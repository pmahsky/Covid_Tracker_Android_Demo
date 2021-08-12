package com.demo.covidtracker.domain.repository

import com.demo.covidtracker.domain.local.CountryDataDao
import com.demo.covidtracker.domain.remote.CountriesDataRemoteDataSource
import com.demo.covidtracker.utils.performGetOperation
import javax.inject.Inject

class CovidRepository @Inject constructor(
    private val remoteDataSource: CountriesDataRemoteDataSource,
    private val localDataSource: CountryDataDao
) {

    fun getAllCountriesData(yesterday: String, sort: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllCountriesData() },
        networkCall = { remoteDataSource.getAllCountriesData(yesterday, sort) },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun getSingleCountryData(country:String) = performGetOperation(
        databaseQuery = {localDataSource.getCountryData(country)},
        networkCall = {remoteDataSource.getSingleCountryData(country)},
        saveCallResult = {localDataSource.insert(it)}
    )
}