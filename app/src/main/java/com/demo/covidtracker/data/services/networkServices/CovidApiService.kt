package com.demo.covidtracker.data.services.networkServices

import com.demo.covidtracker.domain.entities.dbEntities.CountryEntity
import com.demo.covidtracker.domain.entities.dbEntities.HistoricalCountryDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CovidApiService {

    @GET("countries")
    suspend fun getAllCountriesData(
        @Query("yesterday") yesterday: String,
        @Query("sort") sort: String
    ): Response<List<CountryEntity>>

    @GET("countries/{country}")
    suspend fun getSingleCountryData(@Path("country") country: String): Response<CountryEntity>

    @GET("historical/{country}")
    suspend fun getSingleCountryHistoricalData(@Path("country") country: String): Response<HistoricalCountryDataEntity>
}