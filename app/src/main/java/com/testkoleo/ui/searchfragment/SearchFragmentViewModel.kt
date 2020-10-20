package com.testkoleo.ui.searchfragment

import androidx.lifecycle.ViewModel
import com.testkoleo.data.repository.StationsRepository
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(val stationsRepository: StationsRepository) :
    ViewModel() {
    val stations = stationsRepository.getStations()
}