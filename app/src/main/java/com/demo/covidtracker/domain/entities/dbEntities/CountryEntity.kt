package com.demo.covidtracker.domain.entities.dbEntities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
    val id: Int,
    val active: Int,
    val cases: Int,
    @PrimaryKey
    val country: String,
    val critical: Int,
    val deaths: Int,
    val population: Int,
    val recovered: Int,
    val todayCases: Int,
    val todayDeaths: Int,
    val todayRecovered: Int,
    val updated: Long,
    val countryInfo: CountryInfo
)

data class CountryInfo(
    val _id: Int,
    val iso2: String,
    val iso3: String,
    val flag: String
)
