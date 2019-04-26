package com.lz.baselibrary.base

/**
 * @author linzheng
 */
interface ListView : BaseView {

    /**
     * 下拉刷新完成
     */
    fun refreshComplete()

    /**
     * 触发下拉刷新
     */
    fun refreshing()

    //todo loadMore 相关方法

}