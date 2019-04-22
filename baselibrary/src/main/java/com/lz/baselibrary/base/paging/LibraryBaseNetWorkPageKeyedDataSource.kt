package com.lz.baselibrary.base.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.lz.baselibrary.network.UIStatus

/**
 * BasePageKeyedDataSource
 * @author linzheng
 */
abstract class LibraryBaseNetWorkPageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {

    val uiStatus = MutableLiveData<UIStatus>()

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        //ignore...
    }
}