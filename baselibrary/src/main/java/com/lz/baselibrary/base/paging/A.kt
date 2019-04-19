package com.lz.baselibrary.base.paging

import androidx.lifecycle.MutableLiveData
import com.lz.baselibrary.network.LoadMoreStatus
import com.lz.baselibrary.network.NetworkStatus
import com.lz.baselibrary.network.RefreshStatus

/**
 * @author linzheng
 */
//todo  类名还没有想好
class A {

    val networkStatus = MutableLiveData<NetworkStatus>()

    val refreshStatus = MutableLiveData<RefreshStatus>()

    val loadMoreStatus = MutableLiveData<LoadMoreStatus>()

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