package com.lz.baselibrary.base.paging

import androidx.paging.PageKeyedDataSource
import com.lz.baselibrary.network.status.UIStatusData

/**
 * BasePageKeyedDataSource
 * @author linzheng
 */
abstract class LibraryBaseNetWorkPageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {

    val uiStatusData = UIStatusData.create()

    protected var retry: (() -> Any)? = null

    fun retryAllFailed(){
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            it.invoke()
        }
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        //ignore...
    }
}