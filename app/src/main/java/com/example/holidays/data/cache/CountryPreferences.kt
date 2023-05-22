package com.example.holidays.data.cache

import android.content.Context

class CountryPreferences {

    companion object {
        private const val COUNTRY_PREFERENCES = "COUNTRY_PREFERENCES"
        private const val COUNTRY_KEY = "COUNTRY_KEY"
    }

    fun saveCountyCode(code: String, context: Context) {
        val preferences = context.getSharedPreferences(COUNTRY_PREFERENCES, Context.MODE_PRIVATE)
        preferences.edit().putString(COUNTRY_KEY, code).apply()
    }

    fun getCountryCode(context: Context): String {
        val preferences = context.getSharedPreferences(COUNTRY_PREFERENCES, Context.MODE_PRIVATE)
        return preferences.getString(COUNTRY_KEY, "")!!
    }
}