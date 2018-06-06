package com.lz.baselibrary

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lz.baselibrary.base.LibraryBaseActivity
import com.lz.baselibrary.network.Api


class MainActivity : LibraryBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun api(view: View) {
        val api = Api.createApi(UserApi::class)
        print("1111")
    }

    fun viewPagerActivity(view: View) {
        startActivity(Intent(this,ViewPagerActivity::class.java))
    }

}
