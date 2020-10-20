package com.testkoleo.di

import android.content.Context
import android.content.SharedPreferences
import com.testkoleo.data.local.StationDao
import com.testkoleo.data.local.StationDatabase
import com.testkoleo.helpers.Const.Companion.LAST_UPDATE_KEY
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StationsLocalModule(private var context: Context) {
    @Provides
    @Singleton
    fun provideStationDatabase(): StationDatabase = StationDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideStationDao(db: StationDatabase): StationDao = db.stationDao()

    @Provides
    @Singleton
    fun provideSharedPreferences() : SharedPreferences = context.getSharedPreferences(
        LAST_UPDATE_KEY,
        Context.MODE_PRIVATE
    )
}