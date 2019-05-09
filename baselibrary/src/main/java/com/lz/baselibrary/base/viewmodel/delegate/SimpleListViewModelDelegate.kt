package com.lz.baselibrary.base.viewmodel.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus

/**
 * MutableListDelegate
 * @author linzheng
 */
class SimpleListViewModelDelegate(
        private val listData: LiveData<ListData>
) : ListViewModelDelegate {

    override val networkStatus: LiveData<NetworkStatus> by lazy {
        Transformations.switchMap(listData) {
            it.uiStatus.networkStatus
        }
    }
    override val refreshStatus: LiveData<RefreshStatus> by lazy {
        Transformations.switchMap(listData) {
            it.uiStatus.refreshStatus
        }
    }

    override val loadMoreStatus: LiveData<LoadMoreStatus> by lazy {
        Transformations.switchMap(listData) {
            it.uiStatus.loadMoreStatus
        }
    }

    override fun refresh() {
    }

    override fun retry() {
        listData.value?.retry?.invoke()
    }

}