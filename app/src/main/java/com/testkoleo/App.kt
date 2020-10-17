package com.testkoleo

import android.app.Application
import com.testkoleo.di.DaggerAppComponent

class App : Application() {
    val appComponent = DaggerAppComponent.builder()
}