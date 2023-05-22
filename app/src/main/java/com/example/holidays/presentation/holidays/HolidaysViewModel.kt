package com.example.holidays.presentation.holidays

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holidays.data.model.Holidays
import com.example.holidays.domain.GetCountryUseCase
import com.example.holidays.domain.GetHolidaysUseCase
import com.example.holidays.presentation.model.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HolidaysViewModel : ViewModel() {

    private val getCountryUseCase = GetCountryUseCase()
    private val getHolidaysUseCase = GetHolidaysUseCase()

    private val _holidaysLiveData = MutableLiveData<ApiResult>()
    val holidaysLiveData: LiveData<ApiResult> get() = _holidaysLiveData


    fun getHolidays(context: Context, year: String, month: String, day: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val country = getCountryUseCase.execute(context)
            val result = getHolidaysUseCase.execute(country, year, month, day)
            _holidaysLiveData.postValue(result)
        }
    }

    fun getCountryCode(context: Context): String {
        return getCountryUseCase.execute(context)
    }

}