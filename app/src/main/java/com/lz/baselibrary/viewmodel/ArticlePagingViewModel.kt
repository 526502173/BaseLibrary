package com.lz.baselibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lz.baselibrary.base.viewmodel.LibraryBasePagingViewModel
import com.lz.baselibrary.network.data.PagingData
import com.lz.baselibrary.repository.ArticleRepository

/**
 * PagingViewModel
 * @author linzheng
 */
class ArticlePagingViewModel(
        private val repository: ArticleRepository,
        val subscriptionId: MutableLiveData<Int> = MutableLiveData(),
        pagingData: LiveData<PagingData> = Transformations.map(subscriptionId) {
            repository.getArticlePagedList(it)
        }
) : LibraryBasePagingViewModel(
        pagingData
)