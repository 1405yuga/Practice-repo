package com.example.emojis.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

private const val BASE_URL = "https://api.api-ninjas.com/v1/"

val interceptor = Interceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader("X-Api-Key", "JX3pyeEMkSPZtsKRfmUAug==4h78z9WI5AvOvotE")
        .build()
    chain.proceed(request)
}

//create retrofit builder
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient().newBuilder().addInterceptor(interceptor).build())
    .build()

interface EmojiApiService {
    // emoji?group=smileys_emojis
    @GET("emoji?")
    suspend fun getSmileysEmotion(@Query("group") emojisGroup : String): List<Emoji>
}

object EmojiApi{
    val retrofitServiceApi : EmojiApiService by lazy {
        retrofit.create(EmojiApiService::class.java)
    }
}