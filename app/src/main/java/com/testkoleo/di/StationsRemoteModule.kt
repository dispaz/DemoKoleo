package com.testkoleo.di

import com.testkoleo.services.KoleoHeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.http.HttpHeaders
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class StationsRemoteModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(KoleoHeaderInterceptor()).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(client).build()

    companion object{
        private const val BASE_URL = "https://koleo.pl/api/v2/main/"
    }
}