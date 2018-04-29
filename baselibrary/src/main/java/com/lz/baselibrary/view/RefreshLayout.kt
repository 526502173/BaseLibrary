package com.lz.baselibrary.view

/**
 * @author linzheng
 */
interface RefreshLayout {

    /**
     * 刷新成功
     */
    fun refreshComplete()

    /**
     * 主动触发下拉刷新
     */
    fun refreshing()
    /**
     * 是否启用上拉加载
     */
    fun isLoadMoreEnable(isEnable:Boolean)


}
