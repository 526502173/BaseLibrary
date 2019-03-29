package com.lz.baselibrary

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lz.baselibrary.base.LibraryBaseActivity


class MainActivity : LibraryBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun api(view: View) {

    }

    fun viewPagerActivity(view: View) {
        startActivity(Intent(this,ViewPagerActivity::class.java))
    }

}
