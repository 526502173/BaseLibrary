package com.lz.baselibrary.repository

import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.network.PagingData

/**
 * @author linzheng
 */
interface ArticleRepository {

    /**
     * 获取公众号文章列表
     */
    fun getArticleList(subscriptionId: Int): PagingData<Article>

}