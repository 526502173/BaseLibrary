package com.lz.baselibrary.view

/**
 * @author linzheng
 */
interface RefreshListener {

    /**
     * 下拉刷新 Or 上拉加载
     */
    fun refresh(isRefresh: Boolean)

}
