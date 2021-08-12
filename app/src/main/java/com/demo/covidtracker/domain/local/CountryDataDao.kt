package com.demo.covidtracker.domain.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.covidtracker.domain.entities.dbEntities.CountryEntity

@Dao
interface CountryDataDao {

    @Query("SELECT * FROM country")
    fun getAllCountriesData(): LiveData<List<CountryEntity>>

    @Query("SELECT * FROM country WHERE country = :countryName")
    fun getCountryData(countryName: String): LiveData<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryEntity)

}