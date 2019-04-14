
package com.example.helperapp.custom.shimmer

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.helperapp.R
import com.example.helperapp.custom.shimmer.AnimationListener
import com.example.helperapp.custom.shimmer.ShimmerLayout

class ShimmerViewHolder(inflater: LayoutInflater, parent: ViewGroup, innerViewResId: Int) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.viewholder_shimmer, parent, false)) {

    private val mShimmerLayout: ShimmerLayout = itemView as ShimmerLayout

    init {

        inflater.inflate(innerViewResId, mShimmerLayout, true)
    }

    fun setShimmerViewHolderBackground(viewHolderBackground: Drawable?) {
        if (viewHolderBackground != null) {
            setBackground(viewHolderBackground)
        }
    }

    fun setShimmerAnimationDuration(duration: Int) {
        mShimmerLayout.setShimmerAnimationDuration(duration)
    }


    fun bind(animationListener: AnimationListener, position: Int) {
        mShimmerLayout.startShimmerAnimation(animationListener, position)
    }

    private fun setBackground(background: Drawable) {
        mShimmerLayout.background = background
    }
}
