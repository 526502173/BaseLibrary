package com.lz.baselibrary.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * 分页数据的包装类
 * @author linzheng
 */
data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<NetworkState>,
        val refreshSate: LiveData<NetworkState>,
        val refresh: () -> Unit,
        val retry: () -> Unit
)