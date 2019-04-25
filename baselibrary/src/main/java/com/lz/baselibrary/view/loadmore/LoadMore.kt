package com.lz.baselibrary.view.itemdecoration.loadmore

/**
 * 加载更多接口
 * @author linzheng
 */
//todo 删除这个类，使用 NetworkStatus 来替换这个接口的作用
interface LoadMore {

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
     */
    fun normal()

}