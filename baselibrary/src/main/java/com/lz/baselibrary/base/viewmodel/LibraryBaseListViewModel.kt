package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.ViewModel

/**
 * LibraryBaseListViewModel
 * @author linzheng
 */
open class LibraryBaseListViewModel : ViewModel() {

    /**
     * 数据容器
     */
    val mItems: ArrayList<Any> by lazy { ArrayList<Any>() }

    /**
     * 当前是第几页
     */
    var mPage = 1

    /**
     * 下拉刷新调用
     */
    open fun refresh(){
        mPage = 1
        mItems.clear()
    }

    /**
     * 上拉加载调用
     */
    open fun loadMore(){
        mPage ++
    }

}