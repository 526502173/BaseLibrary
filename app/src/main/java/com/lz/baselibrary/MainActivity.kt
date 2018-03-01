package com.lz.baselibrary

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.lz.baselibrary.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val result = Utils.isEmpty(arrayOf("请输入内容"),et_test)

        val list = listOf("")
        val list2 = mutableListOf("")

    }
}
