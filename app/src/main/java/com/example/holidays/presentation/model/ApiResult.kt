package com.example.holidays.presentation.model

import com.example.holidays.data.model.Holidays

sealed class ApiResult {

    class Success(val holidays: Holidays): ApiResult()


    class Error(val message: String): ApiResult()
}