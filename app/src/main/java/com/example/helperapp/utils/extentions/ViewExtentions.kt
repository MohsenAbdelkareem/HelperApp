package com.example.helperapp.utils.extentions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.helperapp.R
import com.google.android.material.tabs.TabLayout
import java.util.regex.Matcher
import java.util.regex.Pattern

// change drawable tint
fun Drawable.changeTint(context: Context, color: Int) {
    val drawable = this
    drawable.mutate()
    drawable.setColorFilter(ContextCompat
            .getColor(context, color), PorterDuff.Mode.SRC_ATOP)
}

// highlighted text
fun TextView.setHighLightedText(textToHighlight: String, color: Int) {
    val textView = this
    val tvt = textView.text.toString()
    var ofe = tvt.indexOf(textToHighlight, 0)
    val wordToSpan = SpannableString(textView.text)

    var ofs = 0
    while (ofs < tvt.length && ofe != -1) {
        ofe = tvt.indexOf(textToHighlight, ofs)
        if (ofe == -1)
            break
        else {
            // you can change or add more span as per your need
            wordToSpan.setSpan(ForegroundColorSpan(textView.context.resources.getColor(color)), ofe, ofe + textToHighlight.length, 0)// set color
            textView.setText(wordToSpan, TextView.BufferType.SPANNABLE)
        }
        ofs = ofe + 1
    }
}

//tabLayout divider
fun TabLayout.addHorizontalDivider(gradientDrawable: GradientDrawable, color: Int) {

    val root = this.getChildAt(0)
    if (root is LinearLayout) {
        root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        gradientDrawable.setColor(this.context.resources.getColor(color))
        gradientDrawable.setSize(2, 1)
        root.dividerPadding = 10
        root.dividerDrawable = gradientDrawable
    }
}

fun ViewPager.autoHandleDirection() {

    this.rotationY = context.resources.getInteger(R.integer.angle_rotation_for_rtl).toFloat()
    this.setPageTransformer(false
    ) { page, _ -> page.rotationY = context.resources.getInteger(R.integer.angle_rotation_for_rtl).toFloat() }
}