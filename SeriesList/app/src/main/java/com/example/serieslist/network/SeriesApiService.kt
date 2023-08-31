package com.example.serieslist.network

import com.example.serieslist.model.SeriesResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://rickandmortyapi.com/api/"

//create retrofit builder
private val retrofitBuilder = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface SeriesApiService {

    //character/?page=2
    @GET("character/?")
    suspend fun getCharacters(@Query("page") pageNo: Int): SeriesResult

}