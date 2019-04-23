package com.lz.baselibrary.network

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * PagingData
 * @author linzheng
 */
data class PagedListData private constructor(
        val pagedList: LiveData<PagedList<Any>>,
        val networkStatus: LiveData<NetworkStatus>,
        val refreshStatus: LiveData<RefreshStatus>,
        val loadMoreStatus: LiveData<LoadMoreStatus>,
        val refresh: () -> Unit,
        val retry: () -> Unit
) {
    companion object {
        fun create(
                pagedList: LiveData<PagedList<Any>>,
                networkStatus: LiveData<NetworkStatus>,
                refreshStatus: LiveData<RefreshStatus>,
                loadMoreStatus: LiveData<LoadMoreStatus>,
                refresh: () -> Unit,
                retry: () -> Unit
        ) = PagedListData(
                pagedList,
                networkStatus,
                refreshStatus,
                loadMoreStatus,
                refresh,
                retry
        )
    }
}