package com.yoesuv.infomadiun.networks

import retrofit2.Response
import java.lang.Exception

abstract class SafeApiRequest {

    suspend fun <T: Any?> apiRequest(call: suspend() -> Response<T>): T? {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw Exception()
        }
    }
}