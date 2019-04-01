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
     * 出发下拉刷新
     */
    fun refreshing()

    /**
     * 上拉加载-没有跟多
     */
    fun loadMoreNoMore()

    /**
     *上拉加载-恢复到可加载状态
     */
    fun loadMoreNormal()

}