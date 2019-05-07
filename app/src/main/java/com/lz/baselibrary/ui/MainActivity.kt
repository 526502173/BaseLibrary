package com.lz.baselibrary.ui

import android.os.Bundle
import android.view.View
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseActivity
import com.lz.baselibrary.setVisibility
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


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
}
