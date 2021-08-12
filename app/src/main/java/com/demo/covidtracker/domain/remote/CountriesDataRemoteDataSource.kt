package com.demo.covidtracker.domain.remote

import com.demo.covidtracker.data.services.networkServices.CovidApiService
import javax.inject.Inject

class CountriesDataRemoteDataSource @Inject constructor(
    private val covidApiService: CovidApiService
) : BaseDataSource() {

    suspend fun getAllCountriesData(yesterday: String, sort: String) =
        getResult { covidApiService.getAllCountriesData(yesterday = yesterday, sort = sort) }

    suspend fun getSingleCountryData(country: String) =
        getResult { covidApiService.getSingleCountryData(country) }

    suspend fun getCountryHistoricalData(country: String) =
        getResult { covidApiService.getSingleCountryHistoricalData(country) }
}