package com.lz.baselibrary.base.paging

import androidx.paging.ItemKeyedDataSource
import com.lz.baselibrary.network.data.DataSourceLiveData

/**
 * LibraryBaseNetWorkItemKeyedDataSource
 * @author linzheng
 */
//todo 暂时未实现
abstract class LibraryBaseNetWorkItemKeyedDataSource<Key, Value> : ItemKeyedDataSource<Key, Value>() {

    val liveData = DataSourceLiveData()

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Value>) {
    }

}