package com.lz.baselibrary.repository.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.lz.baselibrary.api.PageWrapper
import com.lz.baselibrary.api.RespWrapper
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.repository.NetworkState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * @author linzheng
 */
class ArticleDataSource(
        private val mApi: WanAndroidApi,
        private val mSubscriptionId: Int
) : PageKeyedDataSource<Int, Article>() {

    val mNetworkState = MutableLiveData<NetworkState>()

    val mInitialLoad = MutableLiveData<NetworkState>()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
//        mNetworkState.postValue(NetworkState.LOADING)
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
//        mInitialLoad.postValue(NetworkState.LOADING)
//        mNetworkState.postValue(NetworkState.LOADING)
//        try {
//            val response = mApi.getSubscriptionList(1, mSubscriptionId)
//                    .execute()
//            val articleList = response.body()?.data?.datas ?: emptyList()
//            callback.onResult(articleList, null, 2)
//            mNetworkState.postValue(NetworkState.LOADED)
//            mInitialLoad.postValue(NetworkState.LOADED)
//        } catch (ex: Exception) {
//            mNetworkState.postValue(NetworkState.error(ex.message))
//            mInitialLoad.postValue(NetworkState.error(ex.message))
//        }
    }
}


}
