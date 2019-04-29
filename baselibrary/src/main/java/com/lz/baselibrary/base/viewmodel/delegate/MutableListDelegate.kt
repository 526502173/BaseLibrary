package com.lz.baselibrary.base.viewmodel.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus

/**
 * @author linzheng
 */
class MutableListDelegate(
        private val listData: LiveData<ListData>
) : ListDelegate {

    override val networkStatus: LiveData<NetworkStatus>
        get() = Transformations.switchMap(listData) {
            it.networkStatus
        }
    override val refreshStatus: LiveData<RefreshStatus>
        get() = Transformations.switchMap(listData) {
            it.refreshStatus
        }

    override val loadMoreStatus: LiveData<LoadMoreStatus>
        get() = Transformations.switchMap(listData) {
            it.loadMoreStatus
        }

    override fun refresh() {
        listData.value?.refresh?.invoke()
    }

    override fun retry() {
        listData.value?.retry?.invoke()
    }

}