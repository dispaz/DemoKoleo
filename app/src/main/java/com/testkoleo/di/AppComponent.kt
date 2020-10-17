package com.testkoleo.di

import com.testkoleo.MainActivity
import com.testkoleo.data.source.StationsRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [StationsRemoteModule::class])
@Singleton
interface AppComponent {
    fun inject(repository: StationsRepository)
    fun inject(mainActivity: MainActivity)
}