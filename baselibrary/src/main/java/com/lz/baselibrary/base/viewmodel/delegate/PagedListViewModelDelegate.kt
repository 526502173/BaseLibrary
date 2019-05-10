package com.lz.baselibrary.base.viewmodel.delegate

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.network.data.PagingData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

/**
 * PagingDelegate
 * @author linzheng
 */
class PagedListViewModelDelegate(
        private val pagingData: LiveData<PagingData>
) : ListViewModelDelegate, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener {

    override fun onRefresh() {
        pagingData.value?.refresh?.invoke()
    }

    override fun onLoadMore(view: View) {
        //nothing...
    }

    override val networkStatus: LiveData<NetworkStatus> by lazy {
        Transformations.switchMap(pagingData) {
            it.networkStatus
        }
    }
    override val refreshStatus: LiveData<RefreshStatus> by lazy {
        Transformations.switchMap(pagingData) {
            it.refreshStatus
        }
    }

    override val loadMoreStatus: LiveData<LoadMoreStatus> by lazy {
        Transformations.switchMap(pagingData) {
            it.loadMoreStatus
        }
    }

    override fun retry() {
        Completable.create {
            pagingData.value?.retry?.invoke()
            it.onComplete()
        }.subscribeOn(Schedulers.io()).subscribe()
    }

}