package com.example.eynapp.api

import okhttp3.Interceptor
import okhttp3.Response

class EynInterceptor() :
        Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .build()

        return chain.proceed(newRequest)
    }
}