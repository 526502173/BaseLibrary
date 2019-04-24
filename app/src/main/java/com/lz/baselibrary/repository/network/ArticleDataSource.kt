package com.lz.baselibrary.repository.network

import com.lz.baselibrary.EmptyDataException
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.base.paging.LibraryBaseNetWorkPageKeyedDataSource
import com.lz.baselibrary.network.LibraryPagingApiConsumer
import com.lz.baselibrary.network.LoadMoreStatus
import com.lz.baselibrary.network.NetworkStatus
import com.lz.baselibrary.network.RefreshStatus
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author linzheng
 */
class ArticleDataSource(
        private val api: WanAndroidApi,
        private val subscriptionId: Int
) : LibraryBaseNetWorkPageKeyedDataSource<Int, Any>() {

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Any>) {
        Timber.d("ArticleDataSource -> loadAfter()")
        api.getSubscriptionList(params.key, subscriptionId)
                .subscribeOn(Schedulers.io())
                .map { it.data.datas }
                .delay(2, TimeUnit.SECONDS)
                .doOnError {
                    retry = {
                        loadAfter(params, callback)
                    }
                }
                .subscribe(Consumer {
                    retry = null
                    if (it.size < 20) {
                        uiStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_NO_MORE)
                        callback.onResult(it, null)
                    } else {
                        callback.onResult(it, params.key + 1)
                        uiStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_NORMAL)
                    }
                }, LibraryPagingApiConsumer(uiStatusData))
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Any>) {
        Timber.d("ArticleDataSource -> loadInitial()")
        //不知道为什么，在这里必须使用同步调用，不然 Adapter 的 DiffCallback 会不起作用。
        api.getSubscriptionList(1, subscriptionId)
                .map { it.data.datas }
                .doOnNext {
                    if (it.isEmpty()) throw EmptyDataException()
                }
                .doFinally {
                    uiStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_NORMAL)
                    uiStatusData.postRefreshStatus(RefreshStatus.REFRESH_COMPLETE)
                }.doOnError {
                    retry = {
                        loadInitial(params, callback)
                    }
                }.subscribe(Consumer {
                    retry = null
                    uiStatusData.postNetworkStatus(NetworkStatus.SUCCESS)
                    if (it.size < 20) {
                        uiStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_NO_MORE)
                        callback.onResult(it, null, null)
                    } else {
                        callback.onResult(it, null, 2)
                    }
                }, LibraryPagingApiConsumer(uiStatusData))
    }

}
