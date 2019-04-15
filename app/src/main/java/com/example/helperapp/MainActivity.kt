package com.example.helperapp

import com.example.helperapp.base.BaseActivity
import com.example.helperapp.utils.extentions.loadCircleImageGlide
import com.example.helperapp.utils.extentions.loadImageGlide
import com.example.helperapp.utils.extentions.loadRoundCornerImageGlide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun contentView(): Int {
     return R.layout.activity_main
    }

    override fun setUp() {

        imageView.loadCircleImageGlide("http://35.164.33.13//shefz//storage//web//source//thump_dish//MYYQHq5E9t-Q1KWlm5az9NLDuZO2FPhU.jpg")
        imageView1.loadRoundCornerImageGlide("http://35.164.33.13//shefz//storage//web//source//thump_dish//j5zuX6lAVlVgSR06ztMROSVCAYFrJQ-u.jpg")
        imageView2.loadImageGlide("http://35.164.33.13//shefz//storage//web//source//thump_dish//MYYQHq5E9t-Q1KWlm5az9NLDuZO2FPhU.jpg")
    }

}
