package com.demo.covidtracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.demo.covidtracker.domain.repository.CovidRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    repository: CovidRepository
) : ViewModel() {

    val countries = repository.getAllCountriesData("", "")
}
