package com.lz.baselibrary.repository.network

import androidx.lifecycle.MutableLiveData
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

    val sourceLiveData = MutableLiveData<ArticleDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = ArticleDataSource(mApi, mSubscriptionId)
        sourceLiveData.postValue(source)
        return source
    }

}