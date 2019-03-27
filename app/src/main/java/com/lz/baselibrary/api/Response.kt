package com.lz.baselibrary.api

/**
 * @author linzheng
 */

data class Response<T>(
        val data: T,
        val errorCode: Int,
        val errorMsg: String
)

data class Page<T>(
        val curPage: Int,
        val datas: List<T>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)
