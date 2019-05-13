package com.lz.baselibrary.ui

import android.os.Bundle
import android.view.View
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseActivity
import com.lz.baselibrary.view.wrapper.CustomWrapperView
import org.jetbrains.anko.startActivity
import retrofit2.Retrofit
import java.io.IOException


class MainActivity : LibraryBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toPagingActivity(view: View) {
        startActivity<PagingActivity>()
    }

    fun toListActivity(view: View) {
        startActivity<ListActivity>()
    }

    fun showWrapperView(view: View) {
        CustomWrapperView(this).show()
    }
}
