package com.lz.baselibrary.view.itemdecoration.loadmore


/**
 * 加载更多
 * @author linzheng
 */
data class LoadMoreItem(
        var status: Int = LOAD_MORE_STATUS_NORMAL
){
    companion object {
        //普通状态
        const val LOAD_MORE_STATUS_NORMAL = 23334
        //加载中状态
        const val LOAD_MORE_STATUS_LOADING = 23335
        //没有更多咋宏泰
        const val LOAD_MORE_STATUS_NO_MORE = 23336
    }
}