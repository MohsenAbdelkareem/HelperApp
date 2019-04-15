package com.example.helperapp.data.network

import android.database.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ChefApiService {

    @GET("/api/areas")
    fun chefResquest(): Single<Response<BaseResponse>>
}