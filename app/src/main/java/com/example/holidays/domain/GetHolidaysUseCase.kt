package com.example.holidays.domain

import com.example.holidays.data.Repository
import com.example.holidays.data.cache.CountryPreferences
import com.example.holidays.data.cloud.RetrofitInstance
import com.example.holidays.data.model.Holidays
import com.example.holidays.presentation.model.ApiResult

class GetHolidaysUseCase {

    private val repository = Repository(CountryPreferences(), RetrofitInstance.holidaysApi)

    suspend fun execute(country: String, year: String, month: String, day: String): ApiResult {
        return repository.getHoliday(country, year, month, day)
    }

}