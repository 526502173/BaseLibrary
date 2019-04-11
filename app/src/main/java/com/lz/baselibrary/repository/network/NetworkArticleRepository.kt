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

        val livePagedList = factory.toLiveData(pageSize = 20)

        return Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(factory.sourceLiveData) { it.mNetworkState },
                refreshSate = Transformations.switchMap(factory.sourceLiveData) { it.mInitialLoad },
                retry = {},
                refresh = { factory.sourceLiveData.value?.invalidate()   }
        )


    }

}