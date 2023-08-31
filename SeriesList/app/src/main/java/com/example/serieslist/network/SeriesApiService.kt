package com.example.serieslist.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://rickandmortyapi.com/api/character/"

//create retrofit builder
private val retrofitBuilder = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface SeriesApiService {
}