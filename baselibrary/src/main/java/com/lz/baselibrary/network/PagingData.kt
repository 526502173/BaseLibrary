package com.lz.baselibrary.network

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * PagingData
 * @author linzheng
 */
data class PagingData<T> private constructor(
        val pagedList: LiveData<PagedList<T>>,
        val networkStatus: LiveData<NetworkStatus>,
        val refreshStatus: LiveData<RefreshStatus>,
        val loadMoreStatus: LiveData<LoadMoreStatus>,
        val refresh: () -> Unit,
        val retry: () -> Unit
) {
    companion object {
        fun <T> create(
                pagedList: LiveData<PagedList<T>>,
                networkStatus: LiveData<NetworkStatus>,
                refreshStatus: LiveData<RefreshStatus>,
                loadMoreStatus: LiveData<LoadMoreStatus>,
                refresh: () -> Unit,
                retry: () -> Unit
        ) = PagingData(pagedList, networkStatus, refreshStatus, loadMoreStatus, refresh, retry)
    }
}