package com.lz.baselibrary.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.lz.baselibrary.model.wanandroid.Article

/**
 * @author linzheng
 */
interface ArticleRepository {

    /**
     * 获取公众号文章列表
     */
    fun getArticleList(subscriptionId: Int): LiveData<PagedList<Article>>

}