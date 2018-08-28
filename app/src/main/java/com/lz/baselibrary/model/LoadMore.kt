package com.lz.baselibrary.model

/**
 * @author linzheng
 */

//未加载
const val LOAD_MORE_STATUS_NOT_LOAD = 1

//加载中
const val LOAD_MORE_STATUS_LOADING = 2

//加载完成
const val LOAD_MORE_STATUS_LOAD_COMPLETE = 3

//没有更多
const val LOAD_MORE_STATUS_NOT_MORE = 4

data class LoadMore(
        var status: Int = LOAD_MORE_STATUS_NOT_LOAD
)