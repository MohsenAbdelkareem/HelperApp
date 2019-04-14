package com.example.helperapp.custom.shimmer

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.FrameLayout
import com.daimajia.androidanimations.library.Techniques
import com.example.helperapp.custom.shimmer.AnimationListener
import com.example.helperapp.custom.shimmer.MyYoYoAnimation

internal class ShimmerLayout : FrameLayout {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    fun startShimmerAnimation(animationListener: AnimationListener, position: Int) {

        val techniques: Techniques = if(position % 2 == 0){

            Techniques.ZoomIn
        }else{
            Techniques.ZoomIn
        }

        MyYoYoAnimation.with(techniques)
            .duration(300)
            .repeatMode(ValueAnimator.INFINITE)
            .playOn(this@ShimmerLayout, true, animationListener)

    }

    fun setShimmerAnimationDuration(duration: Int) {}
}
