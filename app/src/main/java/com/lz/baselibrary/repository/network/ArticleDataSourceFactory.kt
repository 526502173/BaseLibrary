package com.lz.baselibrary.repository.network

import androidx.paging.DataSource
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.base.paging.LibraryBaseDataSourceFactory
import com.lz.baselibrary.model.wanandroid.Article

/**
 * ArticleDataSourceFactory
 * @author linzheng
 */
class ArticleDataSourceFactory(
        private val api: WanAndroidApi,
        private val subscriptionId: Int
) : LibraryBaseDataSourceFactory<Int, Article, ArticleDataSource>() {

    override fun create(): DataSource<Int, Article> {
        val source = ArticleDataSource(api, subscriptionId)
        sourceLiveData.postValue(source)
        return source
    }

}