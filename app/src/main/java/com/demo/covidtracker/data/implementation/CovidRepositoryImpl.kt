package com.demo.covidtracker.data.implementation

import com.demo.covidtracker.domain.local.CountryDataDao
import com.demo.covidtracker.domain.remote.CountriesDataRemoteDataSource
import com.demo.covidtracker.domain.repository.CovidRepository
import com.demo.covidtracker.utils.performGetOperation
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(
    private val remoteDataSource: CountriesDataRemoteDataSource,
    private val localDataSource: CountryDataDao
) : CovidRepository {
    override fun getAllCountriesData(yesterday: String, sort: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllCountriesData() },
        networkCall = { remoteDataSource.getAllCountriesData(yesterday, sort) },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    override fun getSingleCountryData(country: String) = performGetOperation(
        databaseQuery = { localDataSource.getCountryData(country) },
        networkCall = { remoteDataSource.getSingleCountryData(country) },
        saveCallResult = { localDataSource.insert(it) }
    )
}