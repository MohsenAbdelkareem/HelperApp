package com.example.helperapp.custom.galleryView

import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.MotionEvent
import android.util.AttributeSet
import android.view.animation.Interpolator
import androidx.core.view.MotionEventCompat
import androidx.viewpager.widget.ViewPager
import com.example.helperapp.R
import java.lang.ref.WeakReference


class AutoScrollViewPager : ViewPager {

    private var mHandler: Handler? = null

    /** auto scroll time in milliseconds, default is [.DEFAULT_INTERVAL]  */

    /**
     *
     * get auto scroll time in milliseconds, default is [.DEFAULT_INTERVAL]
     *
     *
     *
     * @return the interval
     */

    /**
     *
     * set auto scroll time in milliseconds, default is [.DEFAULT_INTERVAL]
     *
     *
     *
     * @param interval the interval to set
     */

    var interval = DEFAULT_INTERVAL.toLong()

    /** auto scroll direction, default is [.RIGHT]  */

    private var direction = RIGHT

    /** whether automatic cycle when auto scroll reaching the last or first item, default is true  */

    /**
     *
     * whether automatic cycle when auto scroll reaching the last or first item, default is true
     *
     *
     *
     * @return the isCycle
     */

    /**
     *
     * set whether automatic cycle when auto scroll reaching the last or first item, default is true
     *
     *
     *
     * @param isCycle the isCycle to set
     */

    var isCycle = true

    /** whether stop auto scroll when touching, default is true  */

    /**
     *
     * whether stop auto scroll when touching, default is true
     *
     *
     *
     * @return the stopScrollWhenTouch
     */

    /**
     *
     * set whether stop auto scroll when touching, default is true
     *
     *
     *
     * @param stopScrollWhenTouch
     */

    var isStopScrollWhenTouch = true

    /** how to process when sliding at the last or first item, default is [.SLIDE_BORDER_MODE_NONE]  */

    /**
     *
     * get how to process when sliding at the last or first item
     *
     *
     *
     * @return the slideBorderMode [.SLIDE_BORDER_MODE_NONE], [.SLIDE_BORDER_MODE_TO_PARENT],
     *
     * [.SLIDE_BORDER_MODE_CYCLE], default is [.SLIDE_BORDER_MODE_NONE]
     */

    /**
     *
     * set how to process when sliding at the last or first item
     *
     *
     *
     * @param slideBorderMode [.SLIDE_BORDER_MODE_NONE], [.SLIDE_BORDER_MODE_TO_PARENT],
     *
     * [.SLIDE_BORDER_MODE_CYCLE], default is [.SLIDE_BORDER_MODE_NONE]
     */

    private var slideBorderMode = SLIDE_BORDER_MODE_NONE

    /** whether animating when auto scroll at the last or first item  */

    /**
     *
     * whether animating when auto scroll at the last or first item, default is true
     *
     *
     *
     * @return
     */

    /**
     *
     * set whether animating when auto scroll at the last or first item, default is true
     *
     *
     *
     * @param isBorderAnimation
     */

    private var mAdapter: AutoScrollViewAdapter? = null

    private var isBorderAnimation = true

    /** scroll factor for auto scroll animation, default is 1.0  */

    private var autoScrollFactor = 1.0

    /** scroll factor for swipe scroll animation, default is 1.0  */

    private var swipeScrollFactor = 1.0


    private var isAutoScroll = false

    private var isStopByTouch = false

    private var touchX = 0f
    private var downX = 0f

    private var scroller: CustomDurationScroller? = null


    constructor(paramContext: Context) : super(paramContext){
        init(context, null)
    }


    constructor(paramContext: Context, paramAttributeSet: AttributeSet) : super(paramContext, paramAttributeSet){
        init(context, paramAttributeSet)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.AutoScrollViewPager, 0, 0)
        try {
            interval = a.getInt(R.styleable.AutoScrollViewPager_autoScrollViewPagerInterval, 2000).toLong()
            isCycle = a.getBoolean(R.styleable.AutoScrollViewPager_autoScrollViewPagerIsCycle, false)
            isStopScrollWhenTouch = a.getBoolean(R.styleable.AutoScrollViewPager_autoScrollViewPagerIsStopScrollWhenTouch, false)

        }finally {
            a.recycle()
        }
        mHandler = MyHandler(this)
        mAdapter = AutoScrollViewAdapter(context)
        this.adapter = mAdapter
        setViewPagerScroller()
    }

    fun addAllImages(imagesArray: List<String>){
        mAdapter!!.addAllImages(imagesArray)
    }

    fun addImage(image: String){
        mAdapter!!.addImage(image)
    }


    /**
     *
     * start auto scroll, first scroll delay time is [.getInterval]
     *
     */

    fun startAutoScroll() {

        isAutoScroll = true

        sendScrollMessage((interval + scroller!!.duration / autoScrollFactor * swipeScrollFactor).toLong())

    }


    /**
     *
     * start auto scroll
     *
     *
     *
     * @param delayTimeInMills first scroll delay time
     */

    fun startAutoScroll(delayTimeInMills: Int) {

        isAutoScroll = true

        sendScrollMessage(delayTimeInMills.toLong())

    }


    /**
     *
     * stop auto scroll
     *
     */

    fun stopAutoScroll() {

        isAutoScroll = false

        mHandler!!.removeMessages(SCROLL_WHAT)

    }


    /**
     *
     * set the factor by which the duration of sliding animation will change while swiping
     *
     */

    fun setSwipeScrollDurationFactor(scrollFactor: Double) {

        swipeScrollFactor = scrollFactor

    }


    /**
     *
     * set the factor by which the duration of sliding animation will change while auto scrolling
     *
     */

    fun setAutoScrollDurationFactor(scrollFactor: Double) {

        autoScrollFactor = scrollFactor

    }


    private fun sendScrollMessage(delayTimeInMills: Long) {

        /** remove messages before, keeps one message is running at most  */

        mHandler!!.removeMessages(SCROLL_WHAT)

        mHandler!!.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills)

    }


    /**
     *
     * set ViewPager scroller to change animation duration when sliding
     *
     */

    private fun setViewPagerScroller() {

        try {

            val scrollerField = ViewPager::class.java.getDeclaredField("mScroller")

            scrollerField.isAccessible = true

            val interpolatorField = ViewPager::class.java.getDeclaredField("sInterpolator")

            interpolatorField.isAccessible = true



            scroller = CustomDurationScroller(context, interpolatorField.get(null) as Interpolator)

            scrollerField.set(this, scroller)

        } catch (e: Exception) {

            e.printStackTrace()

        }

    }


    /**
     *
     * scroll only once
     *
     */

    fun scrollOnce() {

        val adapter = adapter

        var currentItem = currentItem

        val totalCount: Int = adapter!!.count

        if ((totalCount == adapter.count) && totalCount <= 1) {

            return

        }


        val nextItem = if (direction == LEFT) --currentItem else ++currentItem

        if (nextItem < 0) {

            if (isCycle) {

                setCurrentItem(totalCount - 1, isBorderAnimation)

            }

        } else if (nextItem == totalCount) {

            if (isCycle) {

                setCurrentItem(0, isBorderAnimation)

            }

        } else {

            setCurrentItem(nextItem, true)

        }

    }


    /**
     *
     *
     *
     * if stopScrollWhenTouch is true
     *
     *  * if event is down, stop auto scroll.
     *
     *  * if event is up, start auto scroll again.
     *
     *
     *
     */

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        val action = MotionEventCompat.getActionMasked(ev)



        if (isStopScrollWhenTouch) {

            if (action == MotionEvent.ACTION_DOWN && isAutoScroll) {

                isStopByTouch = true

                stopAutoScroll()

            } else if (ev.action == MotionEvent.ACTION_UP && isStopByTouch) {

                startAutoScroll()

            }

        }



        if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT || slideBorderMode == SLIDE_BORDER_MODE_CYCLE) {

            touchX = ev.x

            if (ev.action == MotionEvent.ACTION_DOWN) {

                downX = touchX

            }

            val currentItem = currentItem

            val adapter = adapter

            val pageCount = adapter?.count ?: 0

            /**
             *
             * current index is first one and slide to right or current index is last one and slide to left.<br></br>
             *
             * if slide border mode is to parent, then requestDisallowInterceptTouchEvent false.<br></br>
             *
             * else scroll to last one when current item is first one, scroll to first one when current item is last
             *
             * one.
             *
             */

            if (currentItem == 0 && downX <= touchX || currentItem == pageCount - 1 && downX >= touchX) {

                if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT) {

                    parent.requestDisallowInterceptTouchEvent(false)

                } else {

                    if (pageCount > 1) {

                        setCurrentItem(pageCount - currentItem - 1, isBorderAnimation)

                    }

                    parent.requestDisallowInterceptTouchEvent(true)

                }

                return super.dispatchTouchEvent(ev)

            }

        }

        parent.requestDisallowInterceptTouchEvent(true)



        try {

            return super.dispatchTouchEvent(ev)

        } catch (e: IllegalArgumentException) {

            return true

        }

    }


    private class MyHandler(autoScrollViewPager: AutoScrollViewPager) : Handler() {


        private val autoScrollViewPager: WeakReference<AutoScrollViewPager> =
            WeakReference(autoScrollViewPager)


        override fun handleMessage(msg: Message) {

            super.handleMessage(msg)



            when (msg.what) {

                SCROLL_WHAT -> {

                    val pager = this.autoScrollViewPager.get()

                    if (pager != null) {

                        pager.scroller!!.setScrollDurationFactor(pager.autoScrollFactor)

                        pager.scrollOnce()

                        pager.scroller!!.setScrollDurationFactor(pager.swipeScrollFactor)

                        pager.sendScrollMessage(pager.interval + pager.scroller!!.duration)

                    }
                }

                else -> {
                }
            }

        }

    }


    /**
     *
     * get auto scroll direction
     *
     *
     *
     * @return [.LEFT] or [.RIGHT], default is [.RIGHT]
     */

    fun getDirection(): Int {

        return if (direction == LEFT) LEFT else RIGHT

    }


    /**
     *
     * set auto scroll direction
     *
     *
     *
     * @param direction [.LEFT] or [.RIGHT], default is [.RIGHT]
     */

    fun setDirection(direction: Int) {

        this.direction = direction

    }


    override fun performClick(): Boolean {

        return super.performClick()

    }

    companion object {


        const val DEFAULT_INTERVAL = 1500


        const val LEFT = 0

        const val RIGHT = 1


        /** do nothing when sliding at the last or first item  */

        const val SLIDE_BORDER_MODE_NONE = 0

        /** cycle when sliding at the last or first item  */

        const val SLIDE_BORDER_MODE_CYCLE = 1

        /** deliver event to parent when sliding at the last or first item  */

        const val SLIDE_BORDER_MODE_TO_PARENT = 2


        const val SCROLL_WHAT = 0
    }

}