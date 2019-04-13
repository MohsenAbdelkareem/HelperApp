package com.example.helperapp.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView

//object ImageLoader {
//
//    fun loadUserImage(context: Context, url: String, target: ImageView) {
//
//        Glide.with(context)
//                .load(url)
//                .apply(RequestOptions()
//                        .placeholder(R.mipmap.ic_launcher).override(target.width, target.height))
//                .into(target)
//    }
//
//    fun loadFoodImage(context: Context, url: String, target: ImageView) {
//
//        Glide.with(context)
//                .load(url)
//                .apply(RequestOptions()
//                        .placeholder(R.drawable.food_placeholder).override(target.width, target.height))
//                .into(target)
//    }
//
//    fun loadChefImage(context: Context, url: String, target: ImageView) {
//
//        Glide.with(context)
//                .load(url)
//                .apply(RequestOptions()
//                        .placeholder(R.drawable.chef_placeholder).override(target.width, target.height))
//                .into(target)
//    }
//
//    fun loadChefImageWithTransition(context: Context, url: String, target: ImageView) {
//        Glide.with(context)
//                .load(url)
//                .apply(RequestOptions().dontAnimate().override(target.width, target.height))
//                .listener(object : RequestListener<Drawable>() {
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    fun onLoadFailed(@Nullable e: GlideException, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
//                        (context as Activity).startPostponedEnterTransition()
//                        return false
//                    }
//
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
//                        (context as Activity).startPostponedEnterTransition()
//                        return false
//                    }
//                }).into(target)
//        target.scaleType = ImageView.ScaleType.CENTER_CROP
//    }
//
//    fun loadFoodImageWithTransition(context: Context, url: String, target: ImageView) {
//        Glide.with(context)
//                .load(url)
//                .apply(RequestOptions().dontAnimate().override(target.width, target.height))
//                .listener(object : RequestListener<Drawable>() {
//                    fun onLoadFailed(@Nullable e: GlideException, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
//                        (context as Activity).startPostponedEnterTransition()
//                        return false
//                    }
//
//                    fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
//                        (context as Activity).startPostponedEnterTransition()
//                        return false
//                    }
//                }).into(target)
//        target.scaleType = ImageView.ScaleType.CENTER_CROP
//    }
//
//    fun loadUserImageWithTransition(context: Context, url: String, target: ImageView) {
//
//        Glide.with(context)
//                .load(url)
//                .apply(RequestOptions().dontAnimate().override(target.width, target.height))
//                .listener(object : RequestListener<Drawable>() {
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    fun onLoadFailed(@Nullable e: GlideException, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
//                        (context as FragmentActivity).startPostponedEnterTransition()
//                        return false
//                    }
//
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
//                        (context as FragmentActivity).startPostponedEnterTransition()
//                        return false
//                    }
//                }).into(target)
//        target.scaleType = ImageView.ScaleType.CENTER_CROP
//    }
//
//    fun submitImageWithTransition(context: Context, url: String) {
//        Glide.with(context)
//                .load(url)
//                .apply(RequestOptions().dontAnimate())
//                .listener(object : RequestListener<Drawable>() {
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    fun onLoadFailed(@Nullable e: GlideException, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
//                        (context as Activity).startPostponedEnterTransition()
//                        return false
//                    }
//
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
//                        (context as Activity).startPostponedEnterTransition()
//                        return false
//                    }
//                }).submit()
//
//    }
//
//
//}
