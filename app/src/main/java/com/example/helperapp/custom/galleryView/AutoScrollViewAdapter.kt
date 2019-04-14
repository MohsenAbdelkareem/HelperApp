package com.example.helperapp.custom.galleryView

import android.view.ViewGroup
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide


class AutoScrollViewAdapter(private val context: Context) : PagerAdapter() {

    private var imagesArray: MutableList<String> = mutableListOf()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        Glide.with(context).load(imagesArray[position]).into(imageView)

        imageView.setOnClickListener {  }


        container.addView(imageView)



        return imageView

    }


    override fun getCount(): Int {

        return imagesArray.size

    }

    fun addAllImages(imagesArray: List<String>){
        this.imagesArray.clear()
        this.imagesArray.addAll(imagesArray)
        notifyDataSetChanged()
    }

    fun addImage(image: String){
        this.imagesArray.add(image)
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`

    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)

    }


}