package com.example.helperapp.data.network

import android.database.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface DriverApiService {

    @GET("/api/categories")
    fun simpleResquest(): Single<String>
}