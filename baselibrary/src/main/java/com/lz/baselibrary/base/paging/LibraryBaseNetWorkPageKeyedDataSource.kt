package com.lz.baselibrary.base.paging

import androidx.paging.PageKeyedDataSource

/**
 * BasePageKeyedDataSource
 * @author linzheng
 */
abstract class LibraryBaseNetWorkPageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {

    val a = A()

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        //ignore...
    }
}