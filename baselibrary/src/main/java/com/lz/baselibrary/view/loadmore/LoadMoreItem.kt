package com.lz.baselibrary.view.itemdecoration.loadmore

import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.UIStatus


/**
 * 加载更多
 * @author linzheng
 */
data class LoadMoreItem(
        var status: Int = LOAD_MORE_STATUS_NORMAL,
        var failCode: Int = -1
) {
    companion object {
        //普通状态
        const val LOAD_MORE_STATUS_NORMAL = 23334
        //加载中状态
        const val LOAD_MORE_STATUS_LOADING = 23335
        //没有更多
        const val LOAD_MORE_STATUS_NO_MORE = 23336
        //加载更多失败
        const val LOAD_MORE_STATUS_FAIL = 23337
        /**
         * [LoadMoreStatus] 中的 Status 和 [LoadMoreItem] 中的 Status 的映射 Map
         */
        private val LOAD_MORE_STATUS_MAP = mapOf(
                LoadMoreStatus.LOAD_MORE_LOADING.status to LOAD_MORE_STATUS_LOADING,
                LoadMoreStatus.LOAD_MORE_NO_MORE.status to LOAD_MORE_STATUS_NO_MORE,
                UIStatus.LOAD_MORE_FAIL to LOAD_MORE_STATUS_FAIL
        )
    }

    fun bind(newLoadMoreStatus: LoadMoreStatus) {
        val newStatus = LOAD_MORE_STATUS_MAP[newLoadMoreStatus.status] ?: LOAD_MORE_STATUS_NORMAL
        failCode = newLoadMoreStatus.failCode
        status = newStatus
    }

}