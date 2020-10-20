package com.testkoleo.data.repository

import android.content.SharedPreferences
import android.text.format.DateUtils.DAY_IN_MILLIS
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.testkoleo.data.local.StationDao
import com.testkoleo.data.remote.RemoteStationsDataSource
import com.testkoleo.helpers.Const.Companion.DEFAULT_LAST_UPDATE_TIME
import com.testkoleo.helpers.Const.Companion.LAST_UPDATE_KEY
import com.testkoleo.helpers.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class StationsRepository
@Inject constructor(
    private val stationsRemoteSource: RemoteStationsDataSource,
    private val localDataSource: StationDao,
    private val sharedPreferences: SharedPreferences
) {

    fun getStations() = performGetOperation(
        databaseQuery = { localDataSource.getStations() },
        networkCall = { stationsRemoteSource.fetchStations() },
        saveCallResult = { localDataSource.insertStations(it) }
    )

    private fun <T, A> performGetOperation(
        databaseQuery: suspend () -> LiveData<T>,
        networkCall: suspend () -> Resource<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Resource<T>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        if (!needUpdate()) return@liveData

        val response = networkCall.invoke()
        if (response.status == Resource.Status.SUCCESS) {
            saveCallResult(response.data!!)
        } else if (response.status == Resource.Status.ERROR) {
            emit(Resource.error(response.message!!))
            emitSource(source)
        }
    }

    fun needUpdate(): Boolean {
        val currentTimeStamp = System.currentTimeMillis()

        val lastUpdate = sharedPreferences.getLong(LAST_UPDATE_KEY, DEFAULT_LAST_UPDATE_TIME)

        if (lastUpdate == DEFAULT_LAST_UPDATE_TIME || currentTimeStamp < lastUpdate + DAY_IN_MILLIS) {
            sharedPreferences.edit().putLong(LAST_UPDATE_KEY, currentTimeStamp).apply()
            return true
        }

        return false
    }
}