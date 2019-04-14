
package com.example.helperapp.custom.shimmer

import android.content.Context
import android.os.Build
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.example.helperapp.R

class ShimmerRecyclerView : RecyclerView {

    /**
     * Retrieves the actual adapter that contains the data set or null if no adapter is set.
     *
     * @return The actual adapter
     */
    var actualAdapter: RecyclerView.Adapter<*>? = null
        private set
    private var mShimmerAdapter: ShimmerAdapter? = null

    private var mShimmerLayoutManager: RecyclerView.LayoutManager? = null
    private var mActualLayoutManager: RecyclerView.LayoutManager? = null
    private var mLayoutMangerType: LayoutMangerType? = null

    private var mCanScroll: Boolean = false
    var layoutReference: Int = 0
        private set
    private var mGridCount: Int = 0

    val shimmerAdapter: RecyclerView.Adapter<*>?
        get() = mShimmerAdapter

    enum class LayoutMangerType {
        LINEAR_VERTICAL, LINEAR_HORIZONTAL, GRID
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        mShimmerAdapter = ShimmerAdapter(object :
                AnimationListener {
            override fun onAnimationFinished() {
                init(context, attrs)
            }
        })
        val a = context.obtainStyledAttributes(attrs, R.styleable.ShimmerRecyclerView, 0, 0)

        val mShimmerDuration: Int

        try {
            setDemoLayoutReference(
                a.getResourceId(
                    R.styleable.ShimmerRecyclerView_shimmer_demo_layout,
                    R.layout.layout_sample_view
                )
            )
            setDemoChildCount(a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_child_count, 10))
            setGridChildCount(a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_grid_child_count, 2))

            val value = a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_layout_manager_type, 0)
            when (value) {
                0 -> setDemoLayoutManager(LayoutMangerType.LINEAR_VERTICAL)
                1 -> setDemoLayoutManager(LayoutMangerType.LINEAR_HORIZONTAL)
                2 -> setDemoLayoutManager(LayoutMangerType.GRID)
                else -> throw IllegalArgumentException("This value for layout manager is not valid!")
            }

            mShimmerDuration = a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_duration, 1500)
        } finally {
            a.recycle()
        }

        mShimmerAdapter!!.setShimmerDuration(mShimmerDuration)

        showShimmerAdapter(context)
    }

    /**
     * Specifies the number of child should exist in any row of the grid layout.
     *
     * @param count - count specifying the number of child.
     */
    fun setGridChildCount(count: Int) {
        mGridCount = count
    }

    /**
     * Sets the layout manager for the shimmer adapter.
     *
     * @param type layout manager reference
     */
    fun setDemoLayoutManager(type: LayoutMangerType) {
        mLayoutMangerType = type
    }

    /**
     * Sets the number of demo views should be shown in the shimmer adapter.
     *
     * @param count - number of demo views should be shown.
     */
    fun setDemoChildCount(count: Int) {
        mShimmerAdapter!!.setMinItemCount(count)
    }

    /**
     * Specifies the animation duration of shimmer layout.
     *
     * @param duration - count specifying the duration of shimmer in millisecond.
     */
    fun setDemoShimmerDuration(duration: Int) {
        mShimmerAdapter!!.setShimmerDuration(duration)
    }

    /**
     * Specifies the the width of the shimmer line.
     *
     * @param maskWidth - float specifying the width of shimmer line. The value should be from 0 to less or equal to 1.
     * The default value is 0.5.
     */

    /**
     * Sets the shimmer adapter and shows the loading screen.
     */
    private fun showShimmerAdapter(context: Context) {
        mCanScroll = false

        if (mShimmerLayoutManager == null) {
            initShimmerManager()
        }

        layoutManager = mShimmerLayoutManager
        val anim = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
        layoutAnimation = LayoutAnimationController(anim.animation)
        adapter = mShimmerAdapter
    }

    /**
     * Hides the shimmer adapter
     */
    fun hideShimmerAdapter() {
        mCanScroll = true
        layoutManager = mActualLayoutManager
        adapter = actualAdapter
    }

    override fun setLayoutManager(manager: RecyclerView.LayoutManager?) {
        if (manager == null) {
            mActualLayoutManager = null
        } else if (manager !== mShimmerLayoutManager) {
            mActualLayoutManager = manager
        }

        super.setLayoutManager(manager)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        if (adapter == null) {
            actualAdapter = null
        } else if (adapter !== mShimmerAdapter) {
            actualAdapter = adapter
        }

        super.setAdapter(adapter)
    }

    /**
     * Sets the demo layout reference
     *
     * @param mLayoutReference layout resource id of the layout which should be shown as demo.
     */
    fun setDemoLayoutReference(mLayoutReference: Int) {
        this.layoutReference = mLayoutReference
        mShimmerAdapter!!.setLayoutReference(layoutReference)
    }

    private fun initShimmerManager() {
        when (mLayoutMangerType) {
            LayoutMangerType.LINEAR_VERTICAL -> mShimmerLayoutManager =
                object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean {
                        return mCanScroll
                    }
                }
            LayoutMangerType.LINEAR_HORIZONTAL -> mShimmerLayoutManager =
                object : LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
                    override fun canScrollHorizontally(): Boolean {
                        return mCanScroll
                    }
                }
            LayoutMangerType.GRID -> mShimmerLayoutManager =
                object : GridLayoutManager(context, mGridCount) {
                    override fun canScrollVertically(): Boolean {
                        return mCanScroll
                    }
                }
        }
    }

    private fun getColor(id: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getColor(id)
        } else {
            resources.getColor(id)
        }
    }
}
