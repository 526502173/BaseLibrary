package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.base.viewmodel.delegate.PagedListViewModelDelegate
import com.lz.baselibrary.network.data.PagingData
import com.lz.baselibrary.view.loadmore.RetryListener

/**
 * LibraryBasePagingViewModel
 * @author linzheng
 */
open class LibraryBasePagedListViewModel(
        pagingData: LiveData<PagingData>,
        delegate: PagedListViewModelDelegate = PagedListViewModelDelegate(pagingData)
) : CommonListViewModel(delegate),
        SwipeRefreshLayout.OnRefreshListener by delegate,
        RetryListener by delegate {

    val pagedList: LiveData<PagedList<Any>> by lazy {
        Transformations.switchMap(pagingData) {
            it.pagedList
        }
    }
}