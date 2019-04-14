package com.example.helperapp.utils.extentions

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.isEmailValid(): Boolean {
    val pattern: Pattern = Patterns.EMAIL_ADDRESS
    val matcher: Matcher
    matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isMobileValid(): Boolean {

    val mobile = this
    return (mobile.startsWith("012") || mobile.startsWith("011") ||
            mobile.startsWith("010") || mobile.startsWith("015")) && mobile.length == 11
}

fun String.isPasswordValid(): Boolean {

    return this.length >= 6
}