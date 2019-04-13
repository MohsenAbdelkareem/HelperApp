

package com.example.helperapp.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView



object ViewUtils {

    fun pxToDp(px: Float): Float {
        val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
        return px / (densityDpi / 160f)
    }

    fun dpToPx(dp: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return Math.round(dp * density)
    }

    fun changeIconDrawableColor(context: Context, drawable: Drawable?, color: Int) {
        if (drawable != null) {
            drawable.mutate()
            drawable.setColorFilter(ContextCompat
                    .getColor(context, color), PorterDuff.Mode.SRC_ATOP)
        }
    }

    fun setHighLightedText(context: Context, tv: TextView, textToHighlight: String, color: Int) {
        val tvt = tv.text.toString()
        var ofe = tvt.indexOf(textToHighlight, 0)
        val wordToSpan = SpannableString(tv.text)

        var ofs = 0
        while (ofs < tvt.length && ofe != -1) {
            ofe = tvt.indexOf(textToHighlight, ofs)
            if (ofe == -1)
                break
            else {
                // you can change or add more span as per your need
                wordToSpan.setSpan(ForegroundColorSpan(context.resources.getColor(color)), ofe, ofe + textToHighlight.length, 0)// set color
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE)
            }
            ofs = ofe + 1
        }
    }


//    fun addDividerToTabLayout(context: Context, gradientDrawable: GradientDrawable, tabLayout: TabLayout) {
//
//        val root = tabLayout.getChildAt(0)
//        if (root is LinearLayout) {
//            (root as LinearLayout).showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
//            gradientDrawable.setColor(context.resources.getColor(R.color.white))
//            gradientDrawable.setSize(2, 1)
//            (root as LinearLayout).dividerPadding = 10
//            (root as LinearLayout).dividerDrawable = gradientDrawable
//        }
//    }

//    fun showLoadingDialog(context: Context): ProgressDialog {
//        val progressDialog = ProgressDialog(context)
//        progressDialog.show()
//        if (progressDialog.window != null) {
//            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        }
//        progressDialog.setContentView(R.layout.progress_dialog)
//        progressDialog.isIndeterminate = true
//        progressDialog.setCancelable(false)
//        progressDialog.setCanceledOnTouchOutside(false)
//        return progressDialog
//    }

}// This utility class is not publicly instantiable
