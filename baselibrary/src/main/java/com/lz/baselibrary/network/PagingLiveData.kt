package com.lz.baselibrary.network

import androidx.lifecycle.MutableLiveData

/**
 * PagingLiveData
 * @author linzheng
 */
class PagingLiveData {

    val networkStatus = MutableLiveData<NetworkStatus>()

    val refreshStatus = MutableLiveData<RefreshStatus>()

    val loadMoreStatus = MutableLiveData<LoadMoreStatus>()

    //重试
    var retry: (() -> Any)? = null

    /**
     * 错误重试
     */
    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            //todo 这里要使用子线程
            it.invoke()
        }
    }

}