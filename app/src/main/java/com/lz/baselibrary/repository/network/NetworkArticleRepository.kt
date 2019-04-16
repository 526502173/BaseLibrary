package com.lz.baselibrary.repository.network

import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.repository.ArticleRepository
import com.lz.baselibrary.repository.Listing

/**
 * @author linzheng
 */
class NetworkArticleRepository(
        private val mApi: WanAndroidApi
) : ArticleRepository {

    override fun getArticleList(subscriptionId: Int): Listing<Article> {
        val factory = ArticleDataSourceFactory(mApi, subscriptionId)

        //toLiveData 返回的对象其实就是 ComputableLiveData 中的 mLiveDate
        val livePagedList = factory.toLiveData(pageSize = 20)

        return Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(factory.sourceLiveData) { it.mNetworkState },
                refreshSate = Transformations.switchMap(factory.sourceLiveData) { it.mInitialLoad },
                loadMoreState = Transformations.switchMap(factory.sourceLiveData) { it.mAfterLoad },
                retry = {},
                refresh = { factory.sourceLiveData.value?.invalidate() }
        )


    }

}