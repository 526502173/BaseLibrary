package com.lz.baselibrary.repository.network

import androidx.paging.DataSource
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.model.wanandroid.Article

/**
 * @author linzheng
 */
class ArticleDataSourceFactory(
        private val mApi: WanAndroidApi,
        private val mSubscriptionId: Int
) : DataSource.Factory<Int, Article>() {
    override fun create(): DataSource<Int, Article> {
        return ArticleDataSource(mApi,mSubscriptionId)
    }

}