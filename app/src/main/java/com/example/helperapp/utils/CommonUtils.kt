

package com.example.helperapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.transition.TransitionInflater
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.Objects
import java.util.regex.Matcher
import java.util.regex.Pattern

import com.example.helperapp.R



object CommonUtils {

    private val TAG = "CommonUtils"


    fun getVisibleFragment(context: Context, id: Int): Fragment? {
        val navHostFragment = (context as FragmentActivity).supportFragmentManager
                .findFragmentById(id)
        navHostFragment!!.childFragmentManager.fragments[0] != null ?: return navHostFragment.childFragmentManager.fragments[0]
        return null
    }

    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {

        val manager = context.assets
        val `is` = manager.open(jsonFileName)

        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer, charset("UTF-8"))
    }

    fun getTimeStamp(lang: String, calendar: Calendar, format: String): String {
        return SimpleDateFormat(format, Locale(lang)).format(calendar.time)
    }
}// This utility class is not publicly instantiable
