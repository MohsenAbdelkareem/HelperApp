
package com.example.helperapp.custom.shimmer

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ShimmerAdapter(private val animationListener: AnimationListener) : RecyclerView.Adapter<ShimmerViewHolder>() {

    private var mItemCount: Int = 0
    private var mLayoutReference: Int = 0
    private var mShimmerDuration: Int = 0
    private var mShimmerItemBackground: Drawable? = null
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)

        val shimmerViewHolder =
            ShimmerViewHolder(inflater, parent, mLayoutReference)
        shimmerViewHolder.setShimmerViewHolderBackground(mShimmerItemBackground)
        shimmerViewHolder.setShimmerAnimationDuration(mShimmerDuration)

        return shimmerViewHolder
    }

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) {
        holder.bind(animationListener, position)
    }

    override fun getItemCount(): Int {
        return mItemCount
    }

    fun setMinItemCount(itemCount: Int) {
        mItemCount = itemCount
    }

    fun setShimmerDuration(mShimmerDuration: Int) {
        this.mShimmerDuration = mShimmerDuration
    }

    fun setLayoutReference(layoutReference: Int) {
        this.mLayoutReference = layoutReference
    }

}
