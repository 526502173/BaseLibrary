package com.lz.baselibrary.view.itemdecoration.loadmore


/**
 * 加载更多
 * @author linzheng
 */
data class LoadMoreItem(
        var status: Int = LOAD_MORE_STATUS_NORMAL
){
    companion object {
        const val LOAD_MORE_STATUS_NORMAL = 23334
        const val LOAD_MORE_STATUS_LOADING = 23335
        const val LOAD_MORE_STATUS_NO_MORE = 23336
    }
}