package com.testkoleo.helpers

import okhttp3.Interceptor
import okhttp3.Response

class KoleoHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain
            .request()
            .newBuilder()
            .addHeader(KOLEO_HEADER, "1")

        return chain.proceed(builder.build())
    }

    companion object {
        private const val KOLEO_HEADER = "X-KOLEO-Version"
    }
}