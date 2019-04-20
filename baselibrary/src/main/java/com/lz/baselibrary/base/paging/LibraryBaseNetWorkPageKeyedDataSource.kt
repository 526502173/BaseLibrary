package com.lz.baselibrary.base.paging

import androidx.paging.PageKeyedDataSource
import com.lz.baselibrary.network.LoadMoreStatus
import com.lz.baselibrary.network.NetworkStatus
import com.lz.baselibrary.network.PagingLiveData
import com.lz.baselibrary.network.RefreshStatus

/**
 * BasePageKeyedDataSource
 * @author linzheng
 */
//todo 待完善
abstract class LibraryBaseNetWorkPageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {

    val liveData = PagingLiveData()

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        //ignore...
    }

    fun postNetworkStatusValue(value: NetworkStatus) {
        liveData.networkStatus.postValue(value)
    }

    fun postRefreshStatusValue(value: RefreshStatus) {
        liveData.refreshStatus.postValue(value)
    }

    fun postLoadMoreStatusValue(value: LoadMoreStatus) {
        liveData.loadMoreStatus.postValue(value)
    }

}