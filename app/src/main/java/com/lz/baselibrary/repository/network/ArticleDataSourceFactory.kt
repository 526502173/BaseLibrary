package com.lz.baselibrary.repository.network

import androidx.paging.DataSource
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.base.paging.LibraryBaseDataSourceFactory

/**
 * ArticleDataSourceFactory
 * @author linzheng
 */
class ArticleDataSourceFactory(
        private val api: WanAndroidApi,
        private val subscriptionId: Int
) : LibraryBaseDataSourceFactory<Int, Any, ArticleDataSource>() {

    override fun create(): DataSource<Int, Any> {
        val source = ArticleDataSource(api, subscriptionId)
        sourceLiveData.postValue(source)
        return source
    }

}