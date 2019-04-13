package com.example.helperapp.custom

import android.view.ViewGroup
import android.os.Bundle
import android.view.LayoutInflater
import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.helperapp.R
import com.example.helperapp.base.BaseDialog

open class LoadingDialogFragment : BaseDialog(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_dialog, container, false)
    }

    override fun setUp(view: View) {

        isCancelable = false
    }

    companion object {

        private var loadingDialogFragment: LoadingDialogFragment = LoadingDialogFragment()

        fun show(context: Context) {
            val manager = (context as FragmentActivity).supportFragmentManager
            loadingDialogFragment.show(manager, loadingDialogFragment::class.java.name)
        }

        fun dismiss() {
            loadingDialogFragment.dismissDialog(loadingDialogFragment::class.java.name)
        }
    }
}