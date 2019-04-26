package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.lz.baselibrary.network.data.PagedListData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

/**
 * LibraryBasePagingViewModel
 * @author linzheng
 */
//todo 这个类里面用 Lazy 的原因和 Kotlin 机制有关系。建议在所有 override field 都是用 lazy，Kotlin 的 getter 非常奇怪。
abstract class LibraryBasePagingViewModel : NetworkViewModel() {

    abstract val pagingData: LiveData<PagedListData>

    val pagedList: LiveData<PagedList<Any>> by lazy {
        Transformations.switchMap(pagingData) {
            it.pagedList
        }
    }

    override val networkStatus: LiveData<NetworkStatus> by lazy {
        Transformations.switchMap(pagingData) { it.networkStatus }
    }

    val refreshStatus: LiveData<RefreshStatus> by lazy {
        Transformations.switchMap(pagingData) { it.refreshStatus }
    }

    val loadMoreStatus: LiveData<LoadMoreStatus> by lazy {
        Transformations.switchMap(pagingData) { it.loadMoreStatus }
    }

    //Paging 的下拉刷新需要调用此方法
    open fun refresh() {
        pagingData.value?.refresh?.invoke()
    }

    //Paging 的错误重试需要调用此方法
    override fun retry() {
        Completable.create {
            pagingData.value?.retry?.invoke()
            it.onComplete()
        }.subscribeOn(Schedulers.io()).subscribe()
    }
}