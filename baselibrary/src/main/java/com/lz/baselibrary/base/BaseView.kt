package com.lz.baselibrary.base

/**
 * 封装界面状态显示方法
 * 参照 [Gloading]
 * @author linzheng
 */
interface BaseView {

    fun showLoading()

    fun showSuccess()

    fun showLoadFailed()

    fun showEmpty()

}