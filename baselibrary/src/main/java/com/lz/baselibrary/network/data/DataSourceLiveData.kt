package com.lz.baselibrary.network.data

/**
 * PagingLiveData
 * @author linzheng
 */
class DataSourceLiveData {

    //重试
    var retry: (() -> Any)? = null

    /**
     * 错误重试
     */
    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            it.invoke()
        }
    }

}