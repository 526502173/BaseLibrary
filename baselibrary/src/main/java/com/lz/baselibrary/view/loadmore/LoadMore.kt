package com.lz.baselibrary.view.itemdecoration.loadmore

/**
 * @author linzheng
 */
interface LoadMore{

    /**
     * 没有更多了
     */
    fun noMore()

    /**
     * 加载中...
     */
    fun loading()
    /**
     * 普通状态
     * 一般在 Item 不可见时或是 [LoadMoreItem] 刚创建
     */
    fun normal()

}