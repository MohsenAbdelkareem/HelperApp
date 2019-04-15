package com.example.helperapp.data.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface RxCallbacks {

    interface SingleListener{
        fun onSuccess(call: Single<Response<BaseResponse>>, response: BaseResponse, requestType: RequestType)
        fun onFailure(call: Single<Response<BaseResponse>>, requestType: RequestType)
    }

    interface ObservableListener{
        fun onSuccess(call: Observable<Response<BaseResponse>>, response: BaseResponse, requestType: RequestType)
        fun onFailure(call: Observable<Response<BaseResponse>>, requestType: RequestType)
    }
}