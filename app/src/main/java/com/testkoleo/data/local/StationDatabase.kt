package com.testkoleo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.testkoleo.data.models.Station
import com.testkoleo.data.models.StationKeyword

@Database(entities = [Station::class, StationKeyword::class], version = 1, exportSchema = false)
abstract class StationDatabase : RoomDatabase(){
    abstract fun stationDao(): StationDao

    companion object{
        @Volatile
        private var instance: StationDatabase? = null

        fun getDatabase(context: Context): StationDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDb(context).also { instance = it }
            }

        private fun buildDb(appContext: Context) =
            Room.databaseBuilder(appContext, StationDatabase::class.java, "Stations")
                .fallbackToDestructiveMigration()
                .build()
    }
}