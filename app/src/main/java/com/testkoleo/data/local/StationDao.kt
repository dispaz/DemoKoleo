package com.testkoleo.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testkoleo.data.models.Station
import com.testkoleo.data.models.StationKeyword

@Dao
interface StationDao {
    @Query("SELECT * FROM Station")
    suspend fun getStations(): LiveData<List<Station>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(stations: List<Station>)

    @Query("SELECT * FROM StationKeyword")
    suspend fun getStationKeywords(): LiveData<List<StationKeyword>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeywords(stations: List<StationKeyword>)
}