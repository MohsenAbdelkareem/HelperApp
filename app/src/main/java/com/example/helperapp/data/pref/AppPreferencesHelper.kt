package com.example.helperapp.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.helperapp.base.SingletonHolder
import com.example.helperapp.utils.PrefConstants

class AppPreferencesHelper private constructor(context: Context): PreferencesHelper {

    companion object: SingletonHolder<AppPreferencesHelper, Context>(::AppPreferencesHelper)


    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(PrefConstants.PREF_NAME, Context.MODE_PRIVATE)


    override fun setAppLanguage(appLanguage: String?) {
        sharedPreferences.edit().putString(PrefConstants.PREF_KEY_APP_LANG, appLanguage).apply()
    }

    override fun getAppLanguage(): String? =
        sharedPreferences.getString(PrefConstants.PREF_KEY_APP_LANG, "en")


    override fun setAccessToken(accessToken: String?) {
        sharedPreferences.edit().putString(PrefConstants.PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getAccessToken(): String? =
        sharedPreferences.getString(PrefConstants.PREF_KEY_ACCESS_TOKEN, "")


    override fun setFcmToken(fcmToken: String?) {

        sharedPreferences.edit().putString(PrefConstants.PREF_KEY_FCM_TOKEN, fcmToken).apply()
    }

    override fun getFcmToken(): String? =
        sharedPreferences.getString(PrefConstants.PREF_KEY_FCM_TOKEN, "")

}