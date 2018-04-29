package com.lz.baselibrary.base

import android.view.View

/**
 * @author linzheng
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