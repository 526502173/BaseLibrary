package com.lz.baselibrary.base.paging

import androidx.paging.ItemKeyedDataSource

/**
 * LibraryBaseNetWorkItemKeyedDataSource
 * @author linzheng
 */
abstract class LibraryBaseNetWorkItemKeyedDataSource<Key, Value> : ItemKeyedDataSource<Key, Value>() {

    val a = A()

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Value>) {
    }

}