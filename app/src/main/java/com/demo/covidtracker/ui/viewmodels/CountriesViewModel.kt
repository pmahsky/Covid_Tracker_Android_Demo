package com.demo.covidtracker.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.covidtracker.domain.entities.dbEntities.CountryEntity
import com.demo.covidtracker.domain.usecases.GetAllCountriesDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    getAllCountriesDataUseCase: GetAllCountriesDataUseCase
) : ViewModel() {
    private val countriesListMutableLiveData = MutableLiveData<List<CountryEntity>>()
    val countriesListLiveData: LiveData<List<CountryEntity>> = countriesListMutableLiveData

    fun search(query: String) {
        countries.value?.data?.let { countryList ->
            val countryList = countryList.filter {
                it.country.contains(query,true)
            }
            updateCountryList(countryList)
        }
    }

    private fun updateCountryList(countryList: List<CountryEntity>) {
        countriesListMutableLiveData.postValue(countryList)
    }

    val countries = getAllCountriesDataUseCase.invoke()
}
