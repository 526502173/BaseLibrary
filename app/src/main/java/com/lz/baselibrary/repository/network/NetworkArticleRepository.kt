package com.lz.baselibrary.repository.network

import androidx.paging.toLiveData
import com.lz.baselibrary.EmptyDataException
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.network.consumer.paging.LibraryLoadMoreApiConsumer
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.network.data.PagingData
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

    /**
     * 使用非 Paging 的方式获取数据
     */
    override fun getArticleList(subscriptionId: Int, page: Int, listData: ListData) {
        api.getSubscriptionList(page, subscriptionId)
                .delay(if (page == 1) 1L else 2L, TimeUnit.SECONDS)
                .map { it.data.datas }
                .doOnNext {
                    if (it.isEmpty()) throw EmptyDataException()
                }.doOnComplete {
                    listData.uiStatus.postNetworkStatus(NetworkStatus.SUCCESS)
                }.doFinally {
                    listData.uiStatus.postRefreshStatus(RefreshStatus.REFRESH_COMPLETE)
                }.doOnError {
                    //todo retry
                }.map {
                    if (page == 1) it
                    else ArrayList(listData.list.value ?: mutableListOf()).apply {
                        addAll(it)
                    } //todo 这里的逻辑可以尝试通过读 PagedList 的源码来进行优化
                }.subscribe(Consumer {
                    listData.list.postValue(it)
                    if (it.size < 20) listData.uiStatus.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_NO_MORE)
                }, LibraryLoadMoreApiConsumer(listData.uiStatus, page))
    }

    /**
     * 使用 Paging 的方式获取数据
     */
    override fun getArticlePagedList(subscriptionId: Int): PagingData {
        val factory = ArticleDataSourceFactory(api, subscriptionId)
        //toLiveData 返回的对象其实就是 ComputableLiveData 中的 mLiveDate
        val livePagedList = factory.toLiveData(pageSize = 20)
        return PagingData.createFromPageKeyedDataSourceFactory(
                pagedList = livePagedList,
                factory = factory
        )
    }

}