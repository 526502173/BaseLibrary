package com.lz.baselibrary.base.viewmodel.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.lz.baselibrary.network.data.PagedListData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

/**
 * @author linzheng
 */
class PagingDelegate(
        private val pagingData: LiveData<PagedListData>
) : ListDelegate {

    override val networkStatus: LiveData<NetworkStatus>
        get() = Transformations.switchMap(pagingData) {
            it.networkStatus
        }
    override val refreshStatus: LiveData<RefreshStatus>
        get() = Transformations.switchMap(pagingData) {
            it.refreshStatus
        }

    override val loadMoreStatus: LiveData<LoadMoreStatus>
        get() = Transformations.switchMap(pagingData) {
            it.loadMoreStatus
        }

    override fun refresh() {
        pagingData.value?.refresh?.invoke()
    }

    override fun retry() {
        Completable.create {
            pagingData.value?.retry?.invoke()
            it.onComplete()
        }.subscribeOn(Schedulers.io()).subscribe()
    }

}