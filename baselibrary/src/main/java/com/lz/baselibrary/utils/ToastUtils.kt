package com.lz.baselibrary.utils

import android.widget.Toast
import com.lz.baselibrary.LibraryApplication

/**
 * @author linzheng
 */
object ToastUtils {

    private var mToast: Toast? = null

    fun showToast(msg: String) {
        if (mToast == null) {
            mToast = Toast.makeText(LibraryApplication.getInstance(), msg, Toast.LENGTH_SHORT)
        } else {
            mToast?.setText(msg)
        }
        mToast?.show()
    }

}