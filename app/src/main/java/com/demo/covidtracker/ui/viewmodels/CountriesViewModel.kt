package com.demo.covidtracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.demo.covidtracker.domain.usecases.GetAllCountriesDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    getAllCountriesDataUseCase: GetAllCountriesDataUseCase
) : ViewModel() {
    val countries = getAllCountriesDataUseCase.invoke()
}
