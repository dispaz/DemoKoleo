package com.testkoleo.di

import com.testkoleo.data.local.StationDao
import com.testkoleo.ui.MainActivity
import com.testkoleo.data.repository.StationsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StationsRemoteModule::class, StationsLocalModule:: class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}