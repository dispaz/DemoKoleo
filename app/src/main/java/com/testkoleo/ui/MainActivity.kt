package com.testkoleo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.testkoleo.App
import com.testkoleo.R
import com.testkoleo.data.repository.StationsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var stationsRepository: StationsRepository

    private var testJob = Job()
    private val coroutineScope= CoroutineScope(testJob + Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.inject(this@MainActivity)

        coroutineScope.launch {
            stationsRepository.getStations()
        }

    }
}