package com.lz.baselibrary.base

import android.view.View

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/24
 * desc   : BaseView
 * version: 1.0
</pre> *
 */
interface BaseView {

    fun showLoadingDialog()

    fun showLoadingLayout()

    fun hideLoadingDialog()

    fun showEmptyDataLayout()

    fun showErrorLayout()

    fun showSuccessLayout()

    fun reload(v: View?)

}