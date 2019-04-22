package com.lz.baselibrary.network

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * PagingData
 * @author linzheng
 */
data class PagingData<T> private constructor(
        val pagedList: LiveData<PagedList<T>>,
        val uiStatus: LiveData<UIStatus>,
        val refresh: () -> Unit,
        val retry: () -> Unit
) {
    companion object {
        fun <T> create(
                pagedList: LiveData<PagedList<T>>,
                uiStatus: LiveData<UIStatus>,
                refresh: () -> Unit,
                retry: () -> Unit
        ) = PagingData(pagedList, uiStatus, refresh, retry)
    }
}