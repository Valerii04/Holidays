package com.example.holidays.data

import android.content.Context
import com.example.holidays.data.cache.CountryPreferences
import com.example.holidays.data.cloud.HolidaysApi
import com.example.holidays.presentation.model.ApiResult
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

private const val HOLIDAYS_API_KEY = "9af99b0247724f6ba0ed5d90e6a45286"

class Repository(private val countryPreferences: CountryPreferences, private val api: HolidaysApi) {

    fun saveCountryCode(code: String, context: Context) {
        countryPreferences.saveCountyCode(code, context)
    }

    fun getCountryCode(context: Context): String {
        return countryPreferences.getCountryCode(context)
    }

    suspend fun getHoliday(country: String, year: String, month: String, day: String): ApiResult {
        return try {
            ApiResult.Success(api.getVideosByKeyWords(HOLIDAYS_API_KEY, country, year, month, day))
        } catch (e: Exception) {
            if ((e as? HttpException)?.code() == 429) {
                ApiResult.Error("Too many request error!")
            } else if (e is UnknownHostException) {
                ApiResult.Error("No Internet connection!")
            } else {
                ApiResult.Error(e.message.toString())
            }
        }
    }

}