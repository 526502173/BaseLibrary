package com.lz.baselibrary.base

import androidx.lifecycle.ViewModel
import me.drakeet.multitype.Items

/**
 * @author linzheng
 */
open class LibraryBaseListViewModel : ViewModel(){

    val mItems: Items by lazy(LazyThreadSafetyMode.NONE) { Items() }

}