package com.lz.baselibrary.network.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.lz.baselibrary.base.paging.LibraryBaseDataSourceFactory
import com.lz.baselibrary.base.paging.LibraryBaseNetWorkPageKeyedDataSource
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus

/**
 * PagingData
 * @author linzheng
 */
data class PagingData private constructor(
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
        ) = PagingData(
                pagedList,
                networkStatus,
                refreshStatus,
                loadMoreStatus,
                refresh,
                retry
        )

        fun <T : LibraryBaseNetWorkPageKeyedDataSource<Int, Any>> createFromPageKeyedDataSourceFactory(
                pagedList: LiveData<PagedList<Any>>,
                factory: LibraryBaseDataSourceFactory<Int, Any, T>
        ) = create(
                pagedList,
                Transformations.switchMap(factory.sourceLiveData) { it.uiStatusData.networkStatus },
                Transformations.switchMap(factory.sourceLiveData) { it.uiStatusData.refreshStatus },
                Transformations.switchMap(factory.sourceLiveData) { it.uiStatusData.loadMoreStatus },
                { factory.sourceLiveData.value?.invalidate() },
                { factory.sourceLiveData.value?.retryAllFailed() }
        )

    }
}