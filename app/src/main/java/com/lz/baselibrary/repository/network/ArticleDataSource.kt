package com.lz.baselibrary.repository.network

import androidx.paging.PageKeyedDataSource
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.model.wanandroid.Article
import io.reactivex.functions.Consumer

/**
 * @author linzheng
 */
class ArticleDataSource(
        private val mApi: WanAndroidApi,
        private val mSubscriptionId: Int
) : PageKeyedDataSource<Int, Article>() {


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        mApi.getSubscriptionList(params.key, mSubscriptionId)
                .map { it.data.datas }
                .subscribe(Consumer {
                    callback.onResult(it, params.key + 1)
                }, Consumer {

                })
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        mApi.getSubscriptionList(1, mSubscriptionId)
                .map { it.data.datas }
                .subscribe(Consumer {
                    callback.onResult(it, null, 2)
                }, Consumer {
                    //exception
                })
    }

}