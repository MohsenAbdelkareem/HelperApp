package com.example.helperapp.custom.shimmer

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Handler
import androidx.core.view.ViewCompat
import android.view.View
import android.view.animation.Interpolator
import com.daimajia.androidanimations.library.BaseViewAnimator
import com.daimajia.androidanimations.library.Techniques

import java.util.ArrayList

class MyYoYoAnimation private constructor(animationComposer: AnimationComposer) {

    private val animator: BaseViewAnimator?
    private val duration: Long
    private val delay: Long
    private val repeat: Boolean
    private val repeatTimes: Int
    private val repeatMode: Int
    private val interpolator: Interpolator?
    private val pivotX: Float
    private val pivotY: Float
    private val callbacks: List<Animator.AnimatorListener>
    private val target: View?

    init {
        animator = animationComposer.animator
        duration = animationComposer.duration
        delay = animationComposer.delay
        repeat = animationComposer.repeat
        repeatTimes = animationComposer.repeatTimes
        repeatMode = animationComposer.repeatMode
        interpolator = animationComposer.interpolator
        pivotX = animationComposer.pivotX
        pivotY = animationComposer.pivotY
        callbacks = animationComposer.callbacks
        target = animationComposer.target
    }

    interface AnimatorCallback {
        fun call(animator: Animator)
    }

    private open class EmptyAnimatorListener : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {}

        override fun onAnimationEnd(animation: Animator) {}

        override fun onAnimationCancel(animation: Animator) {}

        override fun onAnimationRepeat(animation: Animator) {}
    }

    class AnimationComposer {

        internal val callbacks = ArrayList<Animator.AnimatorListener>()

        internal var animator: BaseViewAnimator? = null
        internal var duration = DURATION

        internal var delay = NO_DELAY
        internal var repeat = false
        internal var repeatTimes = 0
        internal var repeatMode = ValueAnimator.RESTART
        internal var pivotX = com.daimajia.androidanimations.library.YoYo.CENTER_PIVOT
        internal var pivotY = com.daimajia.androidanimations.library.YoYo.CENTER_PIVOT
        internal var interpolator: Interpolator? = null
        internal var target: View? = null

        constructor(techniques: Techniques) {
            this.animator = techniques.animator
        }

        constructor(animator: BaseViewAnimator) {
            this.animator = animator
        }

        fun duration(duration: Long): AnimationComposer {
            this.duration = duration
            return this
        }

        fun delay(delay: Long): AnimationComposer {
            this.delay = delay
            return this
        }

        fun interpolate(interpolator: Interpolator): AnimationComposer {
            this.interpolator = interpolator
            return this
        }

        fun pivot(pivotX: Float, pivotY: Float): AnimationComposer {
            this.pivotX = pivotX
            this.pivotY = pivotY
            return this
        }

        fun pivotX(pivotX: Float): AnimationComposer {
            this.pivotX = pivotX
            return this
        }

        fun pivotY(pivotY: Float): AnimationComposer {
            this.pivotY = pivotY
            return this
        }

        fun repeat(times: Int): AnimationComposer {
            if (times < INFINITE) {
                throw RuntimeException("Can not be less than -1, -1 is infinite loop")
            }
            repeat = times != 0
            repeatTimes = times
            return this
        }

        fun repeatMode(mode: Int): AnimationComposer {
            repeatMode = mode
            return this
        }

        fun withListener(listener: Animator.AnimatorListener): AnimationComposer {
            callbacks.add(listener)
            return this
        }

        fun onStart(callback: AnimatorCallback): AnimationComposer {
            callbacks.add(object : EmptyAnimatorListener() {
                override fun onAnimationStart(animation: Animator) {
                    callback.call(animation)
                }
            })
            return this
        }

        fun onEnd(callback: AnimatorCallback): AnimationComposer {
            callbacks.add(object : EmptyAnimatorListener() {
                override fun onAnimationEnd(animation: Animator) {
                    callback.call(animation)
                }
            })
            return this
        }

        fun onCancel(callback: AnimatorCallback): AnimationComposer {
            callbacks.add(object : EmptyAnimatorListener() {
                override fun onAnimationCancel(animation: Animator) {
                    callback.call(animation)
                }
            })
            return this
        }

        fun onRepeat(callback: AnimatorCallback): AnimationComposer {
            callbacks.add(object : EmptyAnimatorListener() {
                override fun onAnimationRepeat(animation: Animator) {
                    callback.call(animation)
                }
            })
            return this
        }

        fun playOn(target: View?, delay: Boolean?, animationListener: AnimationListener?): YoYoString {
            this.target = target
            return YoYoString(
                MyYoYoAnimation(
                    this
                ).play(delay, animationListener), this.target!!
            )
        }

    }

    /**
     * YoYo string, you can use this string to control your YoYo.
     */
    class YoYoString (private val animator: BaseViewAnimator, private val target: View) {

        val isStarted: Boolean
            get() = animator.isStarted

        val isRunning: Boolean
            get() = animator.isRunning

        @JvmOverloads
        fun stop(reset: Boolean = true) {
            animator.cancel()

            if (reset)
                animator.reset(target)
        }
    }

    private fun play(isDelay: Boolean?, animationListener: AnimationListener?): BaseViewAnimator {
        animator!!.setTarget(target)

        if (pivotX == com.daimajia.androidanimations.library.YoYo.CENTER_PIVOT) {
            ViewCompat.setPivotX(target!!, target.measuredWidth / 2.0f)
        } else {
            target!!.pivotX = pivotX
        }
        if (pivotY == com.daimajia.androidanimations.library.YoYo.CENTER_PIVOT) {
            ViewCompat.setPivotY(target, target.measuredHeight / 2.0f)
        } else {
            target.pivotY = pivotY
        }

        animator.setDuration(duration)
            .addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    if (isDelay!!) {
                        Handler().postDelayed({
                            with(Techniques.FadeOut)
                                .duration(300)
                                .onEnd(object : AnimatorCallback {
                                    override fun call(animator: Animator) {
                                        with(Techniques.FadeIn)
                                            .duration(300)
                                            .onStart(object :
                                                    AnimatorCallback {
                                                override fun call(animator: Animator) {
                                                    animationListener!!.onAnimationFinished()
                                                }
                                            })
                                            .playOn(target, false, null)
                                    }
                                })
                                .playOn(target, false, null)
                        }, 800)
                    }
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
            .setRepeatMode(repeatMode)
            .setInterpolator(interpolator).startDelay = delay

        if (callbacks.size > 0) {
            for (callback in callbacks) {
                animator.addAnimatorListener(callback)
            }
        }
        animator.animate()
        return animator
    }

    companion object {

        private val DURATION = BaseViewAnimator.DURATION
        private val NO_DELAY: Long = 0
        val INFINITE = -1
        val CENTER_PIVOT = java.lang.Float.MAX_VALUE

        fun with(techniques: Techniques): AnimationComposer {
            return AnimationComposer(techniques)
        }

        fun with(animator: BaseViewAnimator): AnimationComposer {
            return AnimationComposer(animator)
        }
    }

}
