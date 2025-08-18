package com.eatclubminiapp.library.network

import retrofit2.Converter
import retrofit2.Retrofit

class RestServiceBuilder(
    private val converterFactory: Converter.Factory
) {

    fun <T> create(kClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
            .create(kClass)
    }

    private companion object {
        const val BASE_URL = "https://eccdn.com.au/"
    }
}