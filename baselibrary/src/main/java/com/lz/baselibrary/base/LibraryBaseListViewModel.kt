package com.lz.baselibrary.base

import android.arch.lifecycle.ViewModel
import me.drakeet.multitype.Items

/**
 * @author linzheng
 */
open class LibraryBaseListViewModel : ViewModel(){

    val mItems: Items by lazy(LazyThreadSafetyMode.NONE) { Items() }

}