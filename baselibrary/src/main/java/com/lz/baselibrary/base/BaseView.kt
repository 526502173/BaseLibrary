package com.lz.baselibrary.base

/**
 * @author linzheng
 */
interface BaseView {

    fun showLoadingDialog()

    fun hideLoadingDialog()

    fun showLoadingLayout()

    fun showEmptyDataLayout()

    fun showErrorLayout()

    fun showSuccessLayout()

}