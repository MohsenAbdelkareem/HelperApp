

package com.example.helperapp.base

import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Dimension.SP
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.helperapp.utils.LocaleHelper
import com.example.helperapp.utils.NetworkUtils
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.Callback {


    override val isNetworkConnected: Boolean
        get() = NetworkUtils.isNetworkConnected(applicationContext)


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        LocaleHelper.setLocale(this, "ar")
        setContentView(contentView())
    }


    override fun onConfigurationChanged(@NonNull newConfig: Configuration) {
        LocaleHelper.setLocale(this, "ar")
        super.onConfigurationChanged(newConfig)
    }

    protected override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }


    protected override fun attachBaseContext(newBase: Context) {
        LocaleHelper.setLocale(newBase, "ar")
        super.attachBaseContext(newBase)
    }

    override fun showLoading() {
        LoadingDialogFragment.show(this)
    }

    override fun hideLoading() {
        LoadingDialogFragment.dismiss()
    }

    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT)
        val sbView = snackBar.view
        val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        textView.gravity = Gravity.START
        textView.setTextSize(SP, 16f)
        snackBar.show()
    }


    override fun onError(message: String) {
        showSnackBar(message)
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun openActivityOnTokenExpire() {
        //        startActivity(SignUpActivity.getStartIntent(this));
        finish()
    }


    protected abstract fun setUp()

    protected abstract fun contentView(): Int

    interface AlertDialogClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog The dialog that received the click.
         * @param id The button that was clicked (e.g.
         * [DialogInterface.BUTTON1]) or the position
         * of the item clicked.
         */
        /* TODO: Change to use BUTTON_POSITIVE after API council */
        fun onClick(dialog: DialogInterface, id: Int)
    }
}
