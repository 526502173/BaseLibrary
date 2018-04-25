package com.lz.baselibrary.view

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/18
 * desc   : 下拉刷新 or 上拉加载
 * version: 1.0
</pre> *
 */
interface RefreshLayout {

    /**
     * 下拉刷新成功
     */
    fun refreshComplete()

    /**
     * 上拉加载成功
     */
    fun loadMoreComplete()

    /**
     * 主动触发下拉刷新
     */
    fun refreshing()
    /**
     * 是否启用上拉加载
     */
    fun isLoadMoreEnable(isEnable:Boolean)


}
