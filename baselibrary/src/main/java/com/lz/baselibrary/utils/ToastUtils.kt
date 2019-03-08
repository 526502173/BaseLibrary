package com.lz.baselibrary.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.lz.baselibrary.LibraryApplication

/**
 * ToastUtils
 * @author linzheng
 */
object ToastUtils {

    /**
     * 主线程的 Handler
     */
    private val mMainHandler = Handler(Looper.getMainLooper())

    /**
     * 静态 Toast 对象
     */
    private var mToast: Toast? = null

    /**
     * 弹出 Toast
     */
    fun showToast(msg: String) {
        mMainHandler.post {
            if (mToast == null) {
                mToast = Toast.makeText(LibraryApplication.app(), msg, Toast.LENGTH_SHORT)
            } else {
                mToast?.setText(msg)
            }
            mToast?.show()
        }
    }

    /**
     * 弹出 Toast
     */
    fun showToast(resId: Int) {
        showToast(LibraryApplication.app().getString(resId))
    }

}