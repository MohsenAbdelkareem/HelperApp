

package com.example.helperapp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment(), MvpView {

    var baseActivity: BaseActivity? = null
        private set


    override val isNetworkConnected: Boolean
        get() = if (baseActivity != null) {
            baseActivity!!.isNetworkConnected
        } else false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context
            this.baseActivity = activity
            activity.onFragmentAttached()
        }
    }

    override fun showLoading() {
        LoadingDialogFragment.show(baseActivity!!)
    }

    override fun hideLoading() {
        LoadingDialogFragment.dismiss()
    }

    override fun onError(message: String) {
        if (baseActivity != null) {
            baseActivity!!.onError(message)
        }
    }

    override fun onError(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.onError(resId)
        }
    }

    override fun showMessage(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(message)
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(resId)
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

    override fun openActivityOnTokenExpire() {
        if (baseActivity != null) {
            baseActivity!!.openActivityOnTokenExpire()
        }
    }


    protected fun getFragmentView(id: Int, inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(id, container, false)
    }

    protected abstract fun setUp(view: View)

    protected abstract fun onPopUp (): Boolean


    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}
