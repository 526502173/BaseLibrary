package com.lz.baselibrary.base.viewmodel.delegate

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.RetryListener

/**
 * MutableListDelegate
 * @author linzheng
 */
class SimpleListViewModelDelegate(
        private val page: MutableLiveData<Int>,
        private val listData: LiveData<ListData>
) : ListViewModelDelegate, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, RetryListener {

    override fun onRetry() {
        listData.value?.retry?.invoke()
    }

    override fun onRefresh() {
        page.value = 1
    }

    override fun onLoadMore(view: View) {
        page.value = page.value?.plus(1)
    }

    override val networkStatus: LiveData<NetworkStatus> by lazy {
        Transformations.switchMap(listData) {
            it.uiStatus.networkStatus
        }
    }
    override val refreshStatus: LiveData<RefreshStatus> by lazy {
        Transformations.switchMap(listData) {
            it.uiStatus.refreshStatus
        }
    }

    override val loadMoreStatus: LiveData<LoadMoreStatus> by lazy {
        Transformations.switchMap(listData) {
            it.uiStatus.loadMoreStatus
        }
    }

}