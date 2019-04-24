package com.lz.baselibrary.repository.network

import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.network.PagedListData
import com.lz.baselibrary.repository.ArticleRepository

/**
 * NetworkArticleRepository
 * @author linzheng
 */
class NetworkArticleRepository(
        private val mApi: WanAndroidApi
) : ArticleRepository {

    override fun getArticleList(subscriptionId: Int): PagedListData {
        val factory = ArticleDataSourceFactory(mApi, subscriptionId)

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