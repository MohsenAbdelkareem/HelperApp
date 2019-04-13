package com.example.helperapp

import android.os.Bundle
import android.os.Handler
import com.example.helperapp.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun setUp() {
    }

    override fun contentView(): Int {
     return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }
}
