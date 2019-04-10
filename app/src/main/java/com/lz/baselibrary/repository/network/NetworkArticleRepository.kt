package com.lz.baselibrary.repository.network

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.repository.ArticleRepository

/**
 * @author linzheng
 */
class NetworkArticleRepository(
        private val mApi: WanAndroidApi
) : ArticleRepository {

    override fun getArticleList(subscriptionId: Int): LiveData<PagedList<Article>> {
        val factory = ArticleDataSourceFactory(mApi, subscriptionId)
        return factory.toLiveData(
                pageSize = 20
        )
    }

}