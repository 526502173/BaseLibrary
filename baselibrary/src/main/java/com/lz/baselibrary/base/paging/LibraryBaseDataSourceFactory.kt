package com.lz.baselibrary.base.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/**
 * LibraryBaseDataSourceFactory
 * @author linzheng
 */
abstract class LibraryBaseDataSourceFactory<Key, Value, T : DataSource<Key, Value>> : DataSource.Factory<Key, Value>() {

    /**
     * 此 LiveData 是为了让 DataSource 能够向 Repo 传递数据
     */
    val sourceLiveData = MutableLiveData<T>()


}