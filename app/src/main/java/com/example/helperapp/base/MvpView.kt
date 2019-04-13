
package com.example.helperapp.base

import android.support.annotation.StringRes

interface MvpView {

    val isNetworkConnected: Boolean

    fun showLoading()

    fun hideLoading()

    fun openActivityOnTokenExpire()

    fun onError(@StringRes resId: Int)

    fun onError(message: String)

    fun showMessage(message: String)

    fun showMessage(@StringRes resId: Int)

    fun hideKeyboard()

}
