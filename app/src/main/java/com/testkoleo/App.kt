package com.testkoleo

import android.app.Application
import com.testkoleo.di.AppComponent
import com.testkoleo.di.DaggerAppComponent
import com.testkoleo.di.StationsLocalModule
import com.testkoleo.di.StationsRemoteModule

class App : Application() {
    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    fun initializeDagger(){
        appComponent = DaggerAppComponent.builder()
            .stationsRemoteModule(StationsRemoteModule())
            .stationsLocalModule(StationsLocalModule(this))
            .build()

    }

}