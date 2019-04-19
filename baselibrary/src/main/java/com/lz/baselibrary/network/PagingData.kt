package com.lz.baselibrary.network

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * PagingData
 * @author linzheng
 */
data class PagingData<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkStatus: NetworkStatus,
        val refreshStatus: RefreshStatus,
        val loadMoreStatus: LoadMoreStatus,
        val refresh: () -> Unit,
        val retry: () -> Unit
)