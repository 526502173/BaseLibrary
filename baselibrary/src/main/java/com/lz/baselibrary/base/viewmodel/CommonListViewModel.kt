package com.lz.baselibrary.base.viewmodel

import com.lz.baselibrary.base.viewmodel.delegate.ListViewModelDelegate

/**
 * @author linzheng
 */
open class CommonListViewModel(
        private val delegate: ListViewModelDelegate
) : NetworkViewModel(), ListViewModelDelegate by delegate