package com.yoesuv.infomadiun.networks


import java.io.IOException
import java.lang.reflect.Type

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 *  Created by yusuf on 7/25/17.
 */

class RxErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    override fun get(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val wrapped = original.get(type, annotations, retrofit) as CallAdapter<out Any, *>
        return RxCallAdapterWrapper(retrofit, wrapped)
    }

    private class RxCallAdapterWrapper<R>(private val retrofit: Retrofit, call: CallAdapter<R, *>) : CallAdapter<R, Observable<R>> {
        private val wrapped: CallAdapter<R, *> = call

        override fun responseType(): Type {
            return wrapped.responseType()
        }

        override fun adapt(call: Call<R>): Observable<R> {
            return (wrapped.adapt(call) as Observable<R>).onErrorResumeNext({ t: Throwable -> Observable.error(asRetrofitException(t)) })
        }

        private fun asRetrofitException(throwable: Throwable): RetrofitException {
            // We had non-200 http error
            if (throwable is HttpException) {
                val response = throwable.response()
                return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit)
            }
            // A network error happened
            return if (throwable is IOException) {
                RetrofitException.networkError(throwable)
            } else RetrofitException.unexpectedError(throwable)

            // We don't know what happened. We need to simply convert to an unknown error
        }

    }

    companion object {

        fun create(): CallAdapter.Factory {
            return RxErrorHandlingCallAdapterFactory()
        }
    }
}