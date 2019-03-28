package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.ViewModel

/**
 * LibraryBaseListViewModel
 * @author linzheng
 */
open class LibraryBaseListViewModel : ViewModel() {

    val mItems: ArrayList<Any> by lazy { ArrayList<Any>() }

    var mPage = 1

}