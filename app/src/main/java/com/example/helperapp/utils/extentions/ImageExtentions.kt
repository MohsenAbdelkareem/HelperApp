package com.example.helperapp.utils.extentions

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
import com.example.helperapp.R

fun ImageView.loadImageGlide(url: String) {

    val target = this
    Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .apply { placeholder(android.R.color.darker_gray).override(target.width, target.height) }
            .into(target)
}

fun ImageView.loadCircleImageGlide(url: String) {

    val target = this
    Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .apply { placeholder(context.resources.getDrawable(R.drawable.circle_placeholder, theme)).override(target.width, target.height)}
            .transform(CircleCrop())
            .into(target)
}

fun ImageView.loadRoundCornerImageGlide(url: String) {

    val target = this
    Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .apply { placeholder(context.resources.getDrawable(R.drawable.round_placeholder, theme)).override(target.width, target.height) }
            .transform(RoundedCorners(50))
            .into(target)
}


fun ImageView.loadImageGlideWithTransition(url: String) {
    val target = this
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

object ImageLoader{
    fun submitImageGlideWithTransition(context: Context, url: String) {
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


