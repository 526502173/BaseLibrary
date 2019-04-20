package com.lz.baselibrary.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseActivity
import okhttp3.Request


class MainActivity : LibraryBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Request.Builder()
                .url("www.baidu.com")
                .build()
    }

    fun api(view: View) {

    }

    fun viewPagerActivity(view: View) {
        startActivity(Intent(this, ViewPagerActivity::class.java))
    }

}
