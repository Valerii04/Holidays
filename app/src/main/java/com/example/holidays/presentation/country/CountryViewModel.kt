package com.example.holidays.presentation.country

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.holidays.domain.GetCountryUseCase
import com.example.holidays.domain.SaveCountryUseCase

class CountryViewModel : ViewModel() {

    private val saveCountryUseCase = SaveCountryUseCase()
    private val getCountryUseCase = GetCountryUseCase()

    fun saveCountryCode(code: String, context: Context) {
        saveCountryUseCase.execute(code, context)
    }

    fun getCountryCode(context: Context): String {
        return getCountryUseCase.execute(context)
    }

}