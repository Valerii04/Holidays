package com.example.holidays.data.cloud

import com.example.holidays.data.model.Holidays
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HolidaysApi {

    @Headers("Content-Type: application/json")
    @GET("/v1/")
    suspend fun getVideosByKeyWords(
        @Query("api_key") key: String,
        @Query("country") country: String,
        @Query("year") year: String,
        @Query("month") month: String,
        @Query("day") day: String
    ): Holidays

}