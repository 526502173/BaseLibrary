package com.lz.baselibrary.base.paging

import androidx.paging.PageKeyedDataSource
import com.lz.baselibrary.network.UIStatusData

/**
 * BasePageKeyedDataSource
 * @author linzheng
 */
abstract class LibraryBaseNetWorkPageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {

    val uiStatusData = UIStatusData.create()

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        //ignore...
    }
}