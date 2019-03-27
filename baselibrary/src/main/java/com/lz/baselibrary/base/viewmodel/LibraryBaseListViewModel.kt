package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.ViewModel
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItems

/**
 * LibraryBaseListViewModel
 * @author linzheng
 */
open class LibraryBaseListViewModel : ViewModel() {

    val mItems: LoadMoreItems by lazy(LazyThreadSafetyMode.NONE) { LoadMoreItems() }

    var mPage = 1

}