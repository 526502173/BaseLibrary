package com.lz.baselibrary.repository

import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.network.data.PagingData

/**
 * @author linzheng
 */
interface ArticleRepository {

    /**
     * 获取公众号文章列表
     */
    fun getArticlePagedList(subscriptionId: Int): PagingData

    /**
     * 获取公众号文章列表非 PagedList
     */
    fun getArticleList(subscriptionId: Int, page: Int, listData: ListData)

}