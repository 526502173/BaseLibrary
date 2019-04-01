package com.lz.baselibrary.view.refresh

/**
 * 下拉刷新接口
 * @author linzheng
 */
interface Refresh{

    /**
     * 下拉刷新完成
     */
    fun complete()

    /**
     * 主动出发下拉刷新
     */
    fun refreshing()

}