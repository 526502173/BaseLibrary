package com.lz.baselibrary.base.viewmodel

import com.lz.baselibrary.base.viewmodel.delegate.ListDelegate

/**
 * @author linzheng
 */
open class CommonListViewModel(
        private val delegate: ListDelegate
) : NetworkViewModel(), ListDelegate by delegate