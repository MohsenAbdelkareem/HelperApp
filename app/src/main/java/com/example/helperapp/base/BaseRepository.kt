package com.example.helperapp.base

import com.example.helperapp.utils.NetworkState

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

 open class BaseRepository {

    val networkStateMutableLiveData: MutableLiveData<NetworkState> by lazy{
        MutableLiveData<NetworkState>()
    }
    val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
}
