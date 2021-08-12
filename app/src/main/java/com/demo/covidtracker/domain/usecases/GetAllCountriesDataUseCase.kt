package com.demo.covidtracker.domain.usecases

import androidx.lifecycle.LiveData
import com.demo.covidtracker.domain.entities.dbEntities.CountryEntity
import com.demo.covidtracker.domain.repository.CovidRepository
import com.demo.covidtracker.data.constants.Constants
import com.demo.covidtracker.utils.Resource
import javax.inject.Inject

class GetAllCountriesDataUseCase @Inject constructor(private val covidRepository: CovidRepository) :
    LiveData<Resource<List<CountryEntity>>>() {

    fun invoke(): LiveData<Resource<List<CountryEntity>>> {
        return covidRepository.getAllCountriesData(
            Constants.QUERY_PARAM_YESTERDAY,
            Constants.QUERY_PARAM_SORT
        )
    }

}