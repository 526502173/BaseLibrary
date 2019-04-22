package com.lz.baselibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.network.PagingData
import com.lz.baselibrary.network.UIStatus
import com.lz.baselibrary.repository.ArticleRepository

/**
 * PagingViewModel
 * @author linzheng
 */
class ArticleViewModel(
        private val mArticleRepository: ArticleRepository
) : ViewModel() {

    val subscriptionId: MutableLiveData<Int> = MutableLiveData()

    val pagingData: LiveData<PagingData<Article>> = Transformations.map(subscriptionId) {
        mArticleRepository.getArticleList(it)
    }

    val uiStatus: LiveData<UIStatus> = Transformations.switchMap(pagingData) { it.uiStatus }

    val livePagedList: LiveData<PagedList<Article>> = Transformations.switchMap(pagingData) {
        it.pagedList
    }

    fun refresh() {
        pagingData.value?.refresh?.invoke()
    }

}