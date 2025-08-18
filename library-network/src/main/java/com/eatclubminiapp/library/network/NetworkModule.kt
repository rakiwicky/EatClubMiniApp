package com.eatclubminiapp.library.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRestServiceBuilder(): RestServiceBuilder  {
        return RestServiceBuilder(
            converterFactory = GsonConverterFactory.create()
        )
    }
}