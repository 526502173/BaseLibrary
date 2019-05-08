package com.lz.baselibrary.view.itemdecoration.loadmore

/**
 * 加载更多接口
 * @author linzheng
 */
interface LoadMore {

    /**
     * 没有更多了
     */
    fun noMore()

    /**
     * 加载中...
     */
    fun loading()

    /**
     * 普通状态
     */
    fun disable()

    /**
     * 准备就绪
     */
    fun readly()

    /**
     * 加载失败
     */
    fun fail(code: Int)

}

/**
 * 网络异常
 */
const val LOAD_MORE_FAIL_CODE_NOT_NETWORK = 2333331

/**
 * Http 异常
 */
const val LOAD_MORE_FAIL_CODE_HTTP = 2333332

/**
 * 其他异常
 */
const val LOAD_MORE_FAIL_CODE_OTHER = 2333333