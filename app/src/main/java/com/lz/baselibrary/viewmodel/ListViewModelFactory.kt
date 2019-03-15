package com.lz.baselibrary.viewmodel

import androidx.lifecycle.ViewModel
import com.lz.baselibrary.base.viewmodel.LibraryBaseListViewModelFactory

/**
 * @author linzheng
 */
class ListViewModelFactory : LibraryBaseListViewModelFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel() as T
    }

}