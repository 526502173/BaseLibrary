package com.lz.baselibrary.base.viewmodel.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.lz.baselibrary.network.data.PagingData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

/**
 * PagingDelegate
 * @author linzheng
 */
class PagingListViewModelDelegate(
        private val pagingData: LiveData<PagingData>
) : ListViewModelDelegate {

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