package com.lz.baselibrary.base.viewmodel

import android.view.View
import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.base.viewmodel.delegate.SimpleListViewModelDelegate
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import timber.log.Timber

/**
 * LibraryBaseListViewModel
 * @author linzheng
 */
open class LibraryBaseListViewModel(
        val page: MutableLiveData<Int> = MutableLiveData(),
        private val listData: LiveData<ListData> = ListData.createLiveData(),
        val list: LiveData<MutableList<Any>> = Transformations.switchMap(listData) {
            it.list
        }
) : CommonListViewModel(SimpleListViewModelDelegate(listData)), SwipeRefreshLayout.OnRefreshListener, LoadMoreListener {

    override fun refresh() {
        page.value = 1
    }

    override fun onRefresh() {
        refresh()
    }

    override fun onLoadMore(view: View) {
        Timber.d("LibraryBaseListViewModel => onLoadMore()")
        page.value = page.value?.plus(1)
    }

    open fun bindPage(lifecycleOwner: LifecycleOwner) {
        page.observe(lifecycleOwner, Observer {
            loadPageData(it, listData.value!!)
        })
    }

    open fun loadPageData(page: Int, listData: ListData) {}

}