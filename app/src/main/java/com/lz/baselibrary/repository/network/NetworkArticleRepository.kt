package com.lz.baselibrary.repository.network

import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.network.PagingData
import com.lz.baselibrary.repository.ArticleRepository

/**
 * NetworkArticleRepository
 * @author linzheng
 */
class NetworkArticleRepository(
        private val mApi: WanAndroidApi
) : ArticleRepository {

    override fun getArticleList(subscriptionId: Int): PagingData<Article> {
        val factory = ArticleDataSourceFactory(mApi, subscriptionId)

        //toLiveData 返回的对象其实就是 ComputableLiveData 中的 mLiveDate
        val livePagedList = factory.toLiveData(pageSize = 20)

        return PagingData.create(
                pagedList = livePagedList,
                uiStatus = Transformations.switchMap(factory.sourceLiveData) { it.uiStatus },
                refresh = {
                    factory.sourceLiveData.value?.invalidate()
                },
                retry = {
                    //todo retry
                }
        )
    }

}