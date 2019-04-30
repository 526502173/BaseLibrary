package com.lz.baselibrary.base.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.base.viewmodel.delegate.MutableListDelegate
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener

/**
 * LibraryBaseListViewModel
 * @author linzheng
 */
open class LibraryBaseListViewModel(
        val page: MutableLiveData<Int> = MutableLiveData(),
        val listData: LiveData<ListData> = ListData.createLiveData(),
        val list: LiveData<MutableList<Any>> = Transformations.switchMap(listData) {
            it.list
        }
) : CommonListViewModel(MutableListDelegate(listData)), SwipeRefreshLayout.OnRefreshListener, LoadMoreListener {

    override fun refresh() {
        page.value = 1
    }

    override fun onRefresh() {
        refresh()
    }

    override fun onLoadMore(view: View) {
        page.value = page.value?.plus(1)
    }

}