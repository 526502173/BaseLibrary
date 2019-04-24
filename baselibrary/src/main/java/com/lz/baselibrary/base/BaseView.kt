package com.lz.baselibrary.base

/**
 * 封装界面状态显示方法
 * 参照 [Gloading]
 * @author linzheng
 */
interface BaseView {

    /**
     * 加载中
     */
    fun showLoading()

    /**
     * 加载成功
     */
    fun showSuccess()

    /**
     * 加载失败
     */
    fun showLoadFailed(status: Int)

    /**
     * 数据为空
     */
    fun showEmpty()

    /**
     * 重试
     */
    fun retry()

}