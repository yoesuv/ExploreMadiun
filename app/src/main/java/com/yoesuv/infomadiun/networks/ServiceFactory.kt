package com.yoesuv.infomadiun.networks

import com.yoesuv.infomadiun.BuildConfig
import com.yoesuv.infomadiun.data.BASE_URL
import com.yoesuv.infomadiun.data.TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  Created by yusuf on 4/12/18.
 */
object ServiceFactory {

    fun create(): RestApi {

        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(logging)
        clientBuilder.callTimeout(TIME_OUT, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        val client = clientBuilder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(RestApi::class.java)
    }
}