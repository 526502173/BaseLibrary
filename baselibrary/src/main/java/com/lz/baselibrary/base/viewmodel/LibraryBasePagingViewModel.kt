package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.lz.baselibrary.base.viewmodel.delegate.PagingListViewModelDelegate
import com.lz.baselibrary.network.data.PagingData

/**
 * LibraryBasePagingViewModel
 * @author linzheng
 */
abstract class LibraryBasePagingViewModel(
        pagingData: LiveData<PagingData>
) : CommonListViewModel(
        PagingListViewModelDelegate(pagingData)
) {

    val pagedList: LiveData<PagedList<Any>> by lazy {
        Transformations.switchMap(pagingData) {
            it.pagedList
        }
    }
}