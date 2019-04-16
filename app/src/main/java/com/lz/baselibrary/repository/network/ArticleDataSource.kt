package com.lz.baselibrary.repository.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.repository.NetworkState
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author linzheng
 */
class ArticleDataSource(
        private val mApi: WanAndroidApi,
        private val mSubscriptionId: Int
) : PageKeyedDataSource<Int, Article>() {

    val mNetworkState = MutableLiveData<NetworkState>()

    val mInitialLoad = MutableLiveData<NetworkState>()

    val mAfterLoad = MutableLiveData<NetworkState>()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        Timber.d("ArticleDataSource -> loadAfter()")
        mNetworkState.postValue(NetworkState.LOADING)
        mApi.getSubscriptionList(params.key, mSubscriptionId)
                .subscribeOn(Schedulers.io())
                .map { it.data.datas }
                .delay(2, TimeUnit.SECONDS)
                .subscribe(Consumer {
                    //                    callback.onResult(it, params.key + 1)
                    callback.onResult(emptyList(), params.key + 1)
                    mAfterLoad.postValue(NetworkState.LOADED)
                }, Consumer {
                    mNetworkState.postValue(NetworkState.error(it.message))
                })
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        Timber.d("ArticleDataSource -> loadInitial()")
        //不知道为什么，在这里必须使用同步调用，不然 Adapter 的 DiffCallback 会不起作用。
        mInitialLoad.postValue(NetworkState.LOADING)
        mNetworkState.postValue(NetworkState.LOADING)
        mApi.getSubscriptionList(1, mSubscriptionId)
                .map { it.data.datas }
                .doOnComplete {
                    mNetworkState.postValue(NetworkState.LOADED)
                    mInitialLoad.postValue(NetworkState.LOADED)
                }.subscribe(Consumer {
                    callback.onResult(it, null, 2)
                }, Consumer {
                    mNetworkState.postValue(NetworkState.error(it.message))
                    mInitialLoad.postValue(NetworkState.error(it.message))
                })
    }

}
