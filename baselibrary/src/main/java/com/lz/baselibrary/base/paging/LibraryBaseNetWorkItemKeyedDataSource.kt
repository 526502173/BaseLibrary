package com.lz.baselibrary.base.paging

import androidx.paging.ItemKeyedDataSource
import com.lz.baselibrary.network.PagingLiveData

/**
 * LibraryBaseNetWorkItemKeyedDataSource
 * @author linzheng
 */
abstract class LibraryBaseNetWorkItemKeyedDataSource<Key, Value> : ItemKeyedDataSource<Key, Value>() {

    val liveData = PagingLiveData()

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Value>) {
    }

}