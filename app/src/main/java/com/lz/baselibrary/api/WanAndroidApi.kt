package com.lz.baselibrary.api

import com.lz.baselibrary.model.wanandroid.SubscriptionArticle
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author linzheng
 */
interface WanAndroidApi {

    /**
     * 获取公众号文章列表
     */
    @GET("/wxarticle/list/{subscriptionId}/{page}/json")
    fun getSubscriptionList(
            @Path("page") page: Int,
            @Path("subscriptionId") subscriptionId: Int
    ): Observable<Response<Page<SubscriptionArticle>>>

}