package com.demo.covidtracker.domain.repository

import androidx.lifecycle.LiveData
import com.demo.covidtracker.domain.entities.dbEntities.CountryEntity
import com.demo.covidtracker.utils.Resource

interface CovidRepository {

    fun getAllCountriesData(yesterday: String, sort: String): LiveData<Resource<List<CountryEntity>>>

    fun getSingleCountryData(country: String): LiveData<Resource<CountryEntity>>
}