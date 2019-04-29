package com.lz.baselibrary.network.data

import androidx.lifecycle.MutableLiveData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus

/**
 * @author linzheng
 */
data class ListData private constructor(
        val list: MutableLiveData<MutableList<Any>>,
        val networkStatus: MutableLiveData<NetworkStatus>,
        val refreshStatus: MutableLiveData<RefreshStatus>,
        val loadMoreStatus: MutableLiveData<LoadMoreStatus>,
        val refresh: () -> Unit,
        val retry: () -> Unit
) {

    companion object {
        fun create(
                list: MutableLiveData<MutableList<Any>> = MutableLiveData(),
                networkStatus: MutableLiveData<NetworkStatus> = MutableLiveData(),
                refreshStatus: MutableLiveData<RefreshStatus> = MutableLiveData(),
                loadMoreStatus: MutableLiveData<LoadMoreStatus> = MutableLiveData(),
                refresh: () -> Unit = {},
                retry: () -> Unit = {}
        ) = ListData(
                list, networkStatus, refreshStatus, loadMoreStatus, refresh, retry
        )
    }

}