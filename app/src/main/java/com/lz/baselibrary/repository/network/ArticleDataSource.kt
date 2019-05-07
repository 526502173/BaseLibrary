package com.lz.baselibrary.repository.network

import com.lz.baselibrary.EmptyDataException
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.base.paging.LibraryBaseNetWorkPageKeyedDataSource
import com.lz.baselibrary.network.consumer.paging.LibraryLoadAfterApiConsumer
import com.lz.baselibrary.network.consumer.paging.LibraryLoadInitialApiConsumer
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * ArticleDataSource
 * @author linzheng
 */
class ArticleDataSource(
        private val api: WanAndroidApi,
        private val subscriptionId: Int
) : LibraryBaseNetWorkPageKeyedDataSource<Int, Any>() {

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Any>) {
        api.getSubscriptionList(params.key, subscriptionId)
                .subscribeOn(Schedulers.io())
                .map { it.data.datas }
                .doOnSubscribe {
                    uiStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_LOADING)
                }.doOnError {
                    retry = {
                        loadAfter(params, callback)
                    }
                }
                .delay(3, TimeUnit.SECONDS)
                .subscribe(Consumer {
                    retry = null
                    if (it.size < 20) {
                        uiStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_NO_MORE)
                        callback.onResult(it, null)
                    } else callback.onResult(it, params.key + 1)
                }, LibraryLoadAfterApiConsumer(uiStatusData))
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Any>) {
        //不知道为什么，在这里必须使用同步调用，不然 Adapter 的 DiffCallback 会不起作用。
        api.getSubscriptionList(1, subscriptionId)
                .map { it.data.datas }
                .doOnNext {
                    if (it.isEmpty()) throw EmptyDataException()
                }
                .doFinally {
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
                        uiStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_NORMAL)
                        callback.onResult(it, null, 2)
                    }
                }, LibraryLoadInitialApiConsumer(uiStatusData))
    }

}
