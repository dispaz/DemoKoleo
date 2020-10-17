package com.testkoleo.services

import retrofit2.http.GET

interface StationsService {
    @GET("station_keywords")
    fun getStationsKeywords(): String
}