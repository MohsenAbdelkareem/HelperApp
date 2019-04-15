package com.example.helperapp.data.network

import retrofit2.Call
import retrofit2.Response

interface RetroResponseListener<T> {
    fun onSuccess(call: Call<T>, response: Response<T>)
    fun onFailure(call: Call<T>)
}