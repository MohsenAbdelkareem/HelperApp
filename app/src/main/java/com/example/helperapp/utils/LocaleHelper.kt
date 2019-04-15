package com.example.helperapp.utils

import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import androidx.annotation.RequiresApi

import java.util.Locale

object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun onAttach(context: Context): Context {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocale(context, lang)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun onAttach(context: Context, defaultLanguage: String): Context {
        val lang = getPersistedData(context, defaultLanguage)
        return setLocale(context, lang)
    }

    fun getLanguage(context: Context): String? {
        return getPersistedData(context, Locale.getDefault().language)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun setLocale(context: Context, language: String?): Context {
        persist(context, language)

        return updateAppResources(context, language)
    }

    private fun getPersistedData(context: Context, defaultLanguage: String): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
    }

    private fun persist(context: Context, language: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()

        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }


    private fun updateAppResources(context: Context, language: String?): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLayoutDirection(locale)
        context.createConfigurationContext(configuration)
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}
