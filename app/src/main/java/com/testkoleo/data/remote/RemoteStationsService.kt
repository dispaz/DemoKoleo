package com.testkoleo.data.remote

import com.testkoleo.data.models.Station
import com.testkoleo.data.models.StationKeyword
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface RemoteStationsService {
    @GET("stations")
    suspend fun getStations() : Deferred<Response<List<Station>>>

    @GET("station_keywords")
    suspend fun getStationsKeywords(): Deferred<Response<List<StationKeyword>>>
}