package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.lz.baselibrary.base.viewmodel.delegate.ListDelegate
import com.lz.baselibrary.base.viewmodel.delegate.PagingDelegate
import com.lz.baselibrary.network.data.PagedListData

/**
 * LibraryBasePagingViewModel
 * @author linzheng
 */
//这个类里面用 Lazy 的原因和 Kotlin 机制有关系。建议在所有 override field 都是用 lazy，Kotlin 的 getter 非常奇怪。
abstract class LibraryBasePagingViewModel : CommonListViewModel() {

    abstract val pagingData: LiveData<PagedListData>

    override val delegate: ListDelegate by lazy {
        PagingDelegate(pagingData)
    }

    val pagedList: LiveData<PagedList<Any>> by lazy {
        Transformations.switchMap(pagingData) {
            it.pagedList
        }
    }

    override fun refresh() {
        delegate.refresh()
    }

    override fun retry() {
        delegate.retry()
    }

}