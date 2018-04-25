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

    private lateinit var mToast: Toast

    fun showToast(msg: String) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT)
        } else {
            mToast.setText(msg)
        }
        mToast.show()
    }

}