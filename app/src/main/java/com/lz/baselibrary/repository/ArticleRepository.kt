package com.lz.baselibrary.repository

import com.lz.baselibrary.network.data.PagedListData

/**
 * @author linzheng
 */
interface ArticleRepository {

    /**
     * 获取公众号文章列表
     */
    fun getArticleList(subscriptionId: Int): PagedListData

}