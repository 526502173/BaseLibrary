package com.lz.baselibrary.base

import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/08
 * desc   : LibraryBaseListFragment
 * version: 1.0
</pre> *
 */
open abstract class LibraryBaseListFragment:LibraryBaseFragment(){

    protected val mAdapter: MultiTypeAdapter = MultiTypeAdapter()

    protected val mItems: Items = Items()

    protected var mPage: Int = 1




}
