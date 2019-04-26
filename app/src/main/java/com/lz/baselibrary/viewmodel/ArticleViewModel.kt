package com.lz.baselibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lz.baselibrary.base.viewmodel.LibraryBasePagingViewModel
import com.lz.baselibrary.network.data.PagedListData
import com.lz.baselibrary.repository.ArticleRepository

/**
 * PagingViewModel
 * @author linzheng
 */
class ArticleViewModel(
        private val mArticleRepository: ArticleRepository
) : LibraryBasePagingViewModel() {

    val subscriptionId: MutableLiveData<Int> = MutableLiveData()

    override val pagingData: LiveData<PagedListData> = Transformations.map(subscriptionId) {
        mArticleRepository.getArticleList(it)
    }

}