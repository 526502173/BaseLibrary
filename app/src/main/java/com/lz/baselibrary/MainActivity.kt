package com.lz.baselibrary

import android.os.Bundle
import android.view.View
import com.lz.baselibrary.base.LibraryBaseActivity


class MainActivity : LibraryBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showLoadingDialog(view: View) {
    }

    fun hideLoadingDialog(View: View) {
    }

}
