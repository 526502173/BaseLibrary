package com.lz.baselibrary

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lz.baselibrary.base.LibraryBaseActivity
import com.lz.baselibrary.utils.ToastUtils
import com.lz.baselibrary.view.Top


class MainActivity : LibraryBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Top()
    }

    fun api(view: View) {
        Thread{
            ToastUtils.showToast("子线程 ShowToast")
        }.start()
    }

    fun viewPagerActivity(view: View) {
        startActivity(Intent(this,ViewPagerActivity::class.java))
    }

}
