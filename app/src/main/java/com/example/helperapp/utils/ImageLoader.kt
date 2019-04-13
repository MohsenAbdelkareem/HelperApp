package com.example.helperapp.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.helperapp.R


object ImageLoader {

    fun loadImage(context: Context, url: String, target: ImageView) {

        Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .apply { placeholder(android.R.color.darker_gray).override(target.width, target.height) }
                .into(target)
    }

    fun loadCircleImage(context: Context, url: String, target: ImageView) {

        Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .transform(CircleCrop())
                .apply { placeholder(android.R.color.darker_gray).override(target.width, target.height) }
                .into(target)
    }

    fun loadRoundCornerImage(context: Context, url: String, target: ImageView) {

        Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .transform(RoundedCorners(50))
                .apply { placeholder(android.R.color.darker_gray).override(target.width, target.height) }
                .into(target)
    }


    fun loadImageWithTransition(context: Context, url: String, target: ImageView) {
        Glide.with(context)
                .load(url).apply {
                    override(target.width, target.height)
                    dontAnimate()
                }
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        (context as Activity).startPostponedEnterTransition()
                        return false                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
                        (context as Activity).startPostponedEnterTransition()
                        return false                    }

                }).into(target)
        target.scaleType = ImageView.ScaleType.CENTER_CROP
    }


    fun submitImageWithTransition(context: Context, url: String) {
        Glide.with(context)
                .load(url)
                .apply {
                    dontAnimate()
                }
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        (context as Activity).startPostponedEnterTransition()
                        return false                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
                        (context as Activity).startPostponedEnterTransition()
                        return false                    }
                }).submit()

    }


}
