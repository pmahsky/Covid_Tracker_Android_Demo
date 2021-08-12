package com.demo.covidtracker.domain.local

import androidx.room.TypeConverter
import com.demo.covidtracker.domain.entities.dbEntities.CountryInfo1
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

class DataConverter : Serializable {
    @TypeConverter // note this annotation
    fun fromCountryInfo(countryInfo: CountryInfo1?): String? {
        if (countryInfo == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<CountryInfo1?>() {}.type
        return gson.toJson(countryInfo, type)
    }

    @TypeConverter // note this annotation
    fun toCountryInfo(countryInfoString: String?): CountryInfo1? {
        if (countryInfoString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<CountryInfo1?>() {}.type
        return gson.fromJson<CountryInfo1>(countryInfoString, type)
    }
}