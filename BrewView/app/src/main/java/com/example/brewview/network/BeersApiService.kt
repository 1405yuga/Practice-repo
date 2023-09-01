package com.example.brewview.network

import com.example.brewview.model.BeersResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.punkapi.com/v2/"

//retrofit builder
private val retrofitBuilder = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface BeersApiService {

    //beers?page=2&per_page=80
    @GET("beers?")
    suspend fun getBeersList(
        @Query("page") pageNo: Int,
        @Query("per_page") perPage: Int
    ): BeersResult

}

object BeersApi{
    val retrofitApiService : BeersApiService by lazy {
        retrofitBuilder.create(BeersApiService::class.java)
    }
}