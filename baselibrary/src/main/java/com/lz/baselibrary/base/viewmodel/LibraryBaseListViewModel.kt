package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.base.viewmodel.delegate.SimpleListViewModelDelegate
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener

/**
 * LibraryBaseListViewModel
 * @author linzheng
 */
open class LibraryBaseListViewModel(
        val page: MutableLiveData<Int> = MutableLiveData(),
        private val listData: LiveData<ListData> = ListData.createLiveData(),
        val list: LiveData<List<Any>> = Transformations.switchMap(listData) {
            it.list
        },
        delegate: SimpleListViewModelDelegate = SimpleListViewModelDelegate(page, listData)
) : CommonListViewModel(delegate), SwipeRefreshLayout.OnRefreshListener by delegate, LoadMoreListener by delegate {

    open fun bindPage(lifecycleOwner: LifecycleOwner) {
        page.observe(lifecycleOwner, Observer {
            loadPageData(it, listData.value!!)
        })
    }

    open fun loadPageData(page: Int, listData: ListData) {}

}