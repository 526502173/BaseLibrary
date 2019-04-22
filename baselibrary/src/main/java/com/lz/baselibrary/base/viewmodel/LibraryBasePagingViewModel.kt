package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.lz.baselibrary.network.PagingData
import com.lz.baselibrary.network.UIStatus

/**
 * LibraryBasePagingViewModel
 * @author linzheng
 */
//todo 这里泛型需要考虑使用 Any 来代替
abstract class LibraryBasePagingViewModel<T> : LiveDataViewModel() {

    abstract val pagingData: LiveData<PagingData<T>>

    val livePagedList: LiveData<PagedList<T>> = Transformations.switchMap(pagingData) {
        it.pagedList
    }

    override val uiStatus: LiveData<UIStatus> = Transformations.switchMap(pagingData) {
        it.uiStatus
    }

}