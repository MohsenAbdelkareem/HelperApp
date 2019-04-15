package com.example.helperapp.data.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetroRequest<T>{

    fun doRetrofitRequest(call: Call<T>, retroResponseListener: RetroResponseListener<T>){

        call.enqueue(object: Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful){
                    retroResponseListener.onSuccess(call, response)
                }else{
                    retroResponseListener.onFailure(call)
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                retroResponseListener.onFailure(call)
            }
        })
    }
}