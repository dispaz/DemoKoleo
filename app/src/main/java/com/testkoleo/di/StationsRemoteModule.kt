package com.testkoleo.di

import com.testkoleo.helpers.KoleoHeaderInterceptor
import com.testkoleo.data.remote.RemoteStationsService
import com.testkoleo.data.remote.RemoteStationsDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class StationsRemoteModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(KoleoHeaderInterceptor()).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()

    @Provides
    @Singleton
    fun provideStationsService(retrofit: Retrofit) : RemoteStationsService =
        retrofit.create(RemoteStationsService::class.java)

    @Provides
    @Singleton
    fun provideRemoteStationsSource(remoteStationsService: RemoteStationsService) =
        RemoteStationsDataSource(remoteStationsService)

    companion object {
        private const val BASE_URL = "https://koleo.pl/api/v2/main/"
    }
}