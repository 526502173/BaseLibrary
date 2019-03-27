package com.lz.baselibrary.utils.initializer

import com.heyooo.heymail.utils.initializer.Initialize

/**
 * @author linzheng
 */
open abstract class SimpleInitialize : Initialize {

    override fun isMultiProcessInitial() = false

}