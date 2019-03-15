package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.ViewModel
import me.drakeet.multitype.Items

/**
 * LibraryBaseListViewModel
 * @author linzheng
 */
open class LibraryBaseListViewModel : ViewModel(){

    val mItems: Items by lazy(LazyThreadSafetyMode.NONE) { Items() }

    var mPage = 1

}