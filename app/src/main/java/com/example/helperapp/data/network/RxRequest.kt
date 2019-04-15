package com.example.helperapp.data.network

import com.example.helperapp.utils.extentions.onMainThread
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response

val compositeDisposable: CompositeDisposable by lazy {
    CompositeDisposable()
}

object RxRequest{

    // Observable Request
    fun callApi(call: Observable<Response<BaseResponse>>,
                rxObservableListener: RxCallbacks.ObservableListener,
                requestType: RequestType){


        compositeDisposable.add(call.onMainThread().subscribe(
                { t ->

                    if (t.isSuccessful) {
                        // TODO handle Response and RollBar Message
                        rxObservableListener.onSuccess(call, t.body()!!, requestType)
                    }else{

                        when(t.code()){
                            0 -> {
                                // TODO handle Error by status code
                            }
                        }

                        // TODO handle Error and RollBar Message
                        rxObservableListener.onFailure(call, requestType)
                    }
                },
                {
                    // TODO handle Error and RollBar Message
                    rxObservableListener.onFailure(call, requestType)
                }))
    }

    // Single Request
    fun callApi(call: Single<Response<BaseResponse>>,
                rxSingleListener: RxCallbacks.SingleListener,
                requestType: RequestType){


        compositeDisposable.add(call.onMainThread().subscribe(
                { t ->
                    if (t.isSuccessful) {
                        // TODO handle Response and RollBar Message
                        rxSingleListener.onSuccess(call, t.body()!!, requestType)
                    }else{

                        when(t.code()){
                            0 -> {
                                // TODO handle Error by status code
                            }
                        }

                        // TODO handle Error and RollBar Message
                        rxSingleListener.onFailure(call, requestType)
                    }
                },
                {
                    // TODO handle Error and RollBar Message
                    rxSingleListener.onFailure(call, requestType)
                }))
    }

    // Clear Tasks
    fun clear(){
        compositeDisposable.clear()
    }
}