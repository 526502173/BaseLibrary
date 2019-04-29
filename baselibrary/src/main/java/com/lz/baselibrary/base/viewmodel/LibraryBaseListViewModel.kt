package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import com.lz.baselibrary.base.viewmodel.delegate.ListDelegate
import com.lz.baselibrary.base.viewmodel.delegate.MutableListDelegate
import com.lz.baselibrary.network.data.ListData

/**
 * LibraryBaseListViewModel
 * @author linzheng
 */
abstract class LibraryBaseListViewModel : CommonListViewModel() {

    abstract val listData: LiveData<ListData>

    override val delegate: ListDelegate by lazy {
        MutableListDelegate(listData)
    }

    override fun refresh() {
        delegate.retry()
    }

    override fun retry() {
        delegate.retry()
    }

}