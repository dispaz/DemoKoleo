package com.testkoleo.data.remote

import javax.inject.Inject

class RemoteStationsDataSource @Inject constructor(private val stationsService: RemoteStationsService) :
    BaseDataSource() {
    suspend fun fetchStations() = getResult { stationsService.getStations().await() }
    suspend fun fetchStationsKeywords() = getResult { stationsService.getStations().await() }
}
