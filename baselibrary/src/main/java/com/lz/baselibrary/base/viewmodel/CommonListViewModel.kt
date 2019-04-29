package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import com.lz.baselibrary.base.viewmodel.delegate.ListDelegate
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus

/**
 * @author linzheng
 */
abstract class CommonListViewModel : NetworkViewModel() {

    abstract val delegate: ListDelegate

    override val networkStatus: LiveData<NetworkStatus> by lazy {
        delegate.networkStatus
    }

    val refreshStatus: LiveData<RefreshStatus> by lazy {
        delegate.refreshStatus
    }

    val loadMoreStatus: LiveData<LoadMoreStatus> by lazy {
        delegate.loadMoreStatus
    }

    abstract fun refresh()

}