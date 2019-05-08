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

    /**
     * 上拉加载失败
     * [code] 失败原因
     */
    fun loadMoreFail(code: Int)

    /**
     * 上拉加载没有更多
     */
    fun loadMoreNoMore()

    /**
     * 上拉加载普通状态
     */
    fun loadMoreNormal()

    /**
     * 上拉加载中...
     */
    fun loadingMore()

    /**
     * 可以触发上拉加载
     */
    fun loadMoreReady()

}