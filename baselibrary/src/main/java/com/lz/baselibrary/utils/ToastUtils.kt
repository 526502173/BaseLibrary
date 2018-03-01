package com.lz.baselibrary.utils

import android.widget.Toast
import com.lz.baselibrary.MyApplication

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/08
 *     desc   : ToastUtils
 *     version: 1.0
 * </pre>
 */

object ToastUtils {

    private val mToast: Toast by lazy(LazyThreadSafetyMode.NONE) {
        Toast.makeText(MyApplication.applicationContext, "", Toast.LENGTH_SHORT)
    }

    fun showToast(msg: String) {
        mToast.run {
            setText(msg)
            show()
        }
    }

}