package com.example.holidays.domain

import android.content.Context
import com.example.holidays.data.Repository
import com.example.holidays.data.cache.CountryPreferences
import com.example.holidays.data.cloud.RetrofitInstance

class GetCountryUseCase {

    private val repository = Repository(CountryPreferences(), RetrofitInstance.holidaysApi)

    fun execute(context: Context): String {
        return repository.getCountryCode(context)
    }

}