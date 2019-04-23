package com.lz.baselibrary.network

import androidx.lifecycle.MutableLiveData

/**
 * UIStatus Data
 * @author linzheng
 */
data class UIStatusData private constructor(
        val networkStatus: MutableLiveData<NetworkStatus>,
        val refreshStatus: MutableLiveData<RefreshStatus>,
        val loadMoreStatus: MutableLiveData<LoadMoreStatus>
) {

    fun postNetworkStatus(newValue: NetworkStatus) {
        networkStatus.postValue(newValue)
    }

    fun postRefreshStatus(newValue: RefreshStatus) {
        refreshStatus.postValue(newValue)
    }

    fun postLoadMoreStatus(newValue: LoadMoreStatus) {
        loadMoreStatus.postValue(newValue)
    }

    companion object {
        fun create(
                networkStatus: MutableLiveData<NetworkStatus> = MutableLiveData(),
                refreshStatus: MutableLiveData<RefreshStatus> = MutableLiveData(),
                loadMoreStatus: MutableLiveData<LoadMoreStatus> = MutableLiveData()
        ): UIStatusData {
            return UIStatusData(networkStatus, refreshStatus, loadMoreStatus)
        }
    }

}