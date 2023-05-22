package com.example.holidays.data.cloud

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val holidaysApi: HolidaysApi = Retrofit.Builder()
        .baseUrl("https://holidays.abstractapi.com")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(OkHttpClient.Builder().build())
        .build()
        .create(HolidaysApi::class.java)
}