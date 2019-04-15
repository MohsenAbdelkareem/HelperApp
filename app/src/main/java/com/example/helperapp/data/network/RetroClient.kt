package com.example.helperapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {

    private val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val requestInterface: Retrofit = Retrofit.Builder()
            .baseUrl("http://shehab.codanyon.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    val DRIVER_API_SERVICE: DriverApiService = requestInterface.create(DriverApiService::class.java)
    val CHEF_API_SERVICE: ChefApiService = requestInterface.create(ChefApiService::class.java)

}