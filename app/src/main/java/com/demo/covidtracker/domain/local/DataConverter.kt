package com.demo.covidtracker.domain.local

import androidx.room.TypeConverter
import com.demo.covidtracker.domain.entities.dbEntities.CountryInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

class DataConverter : Serializable {
    @TypeConverter // note this annotation
    fun fromCountryInfo(countryInfo: CountryInfo?): String? {
        if (countryInfo == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<CountryInfo?>() {}.type
        return gson.toJson(countryInfo, type)
    }

    @TypeConverter // note this annotation
    fun toCountryInfo(countryInfoString: String?): CountryInfo? {
        if (countryInfoString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<CountryInfo?>() {}.type
        return gson.fromJson<CountryInfo>(countryInfoString, type)
    }
}