package com.lz.baselibrary.repository.network

import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.lz.baselibrary.EmptyDataException
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.mainThreadScheduler
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.network.data.PagedListData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus
import com.lz.baselibrary.repository.ArticleRepository
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

/**
 * NetworkArticleRepository
 * @author linzheng
 */
class NetworkArticleRepository(
        private val api: WanAndroidApi
) : ArticleRepository {

    override fun getArticleList(page: Int): ListData {
        val listData = ListData.create()
        //todo retry 和 refresh 的实现
        //Pageing 的方法应该和非 Paging 的分开实现
        api.getSubscriptionList(1, page)
                .delay(2, TimeUnit.SECONDS)
                .map { it.data.datas }
                .doOnNext {
                    if (it.isEmpty()) throw EmptyDataException()
                }
                .observeOn(mainThreadScheduler)
                .doFinally {
                    listData.refreshStatus.postValue(RefreshStatus.REFRESH_COMPLETE)
                }.subscribe(Consumer {
                    listData.networkStatus.postValue(NetworkStatus.SUCCESS)
                    if (it.size < 20) listData.loadMoreStatus.postValue(LoadMoreStatus.LOAD_MORE_NO_MORE)
                    else listData.loadMoreStatus.postValue(LoadMoreStatus.LOAD_MORE_NORMAL)
                    listData.list.postValue(it.toMutableList())
                }, Consumer {
                    //todo 待实现
                })
        return listData
    }

    /**
     * Paging 中的
     */
    override fun getArticlePagedList(subscriptionId: Int): PagedListData {
        val factory = ArticleDataSourceFactory(api, subscriptionId)

        //toLiveData 返回的对象其实就是 ComputableLiveData 中的 mLiveDate
        val livePagedList = factory.toLiveData(pageSize = 20)

        return PagedListData.create(
                pagedList = livePagedList,
                networkStatus = Transformations.switchMap(factory.sourceLiveData) { it.uiStatusData.networkStatus },
                refreshStatus = Transformations.switchMap(factory.sourceLiveData) { it.uiStatusData.refreshStatus },
                loadMoreStatus = Transformations.switchMap(factory.sourceLiveData) { it.uiStatusData.loadMoreStatus },
                refresh = {
                    factory.sourceLiveData.value?.invalidate()
                },
                retry = {
                    factory.sourceLiveData.value?.retryAllFailed()
                }
        )
    }

}