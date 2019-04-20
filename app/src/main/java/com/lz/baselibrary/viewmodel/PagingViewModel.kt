package com.lz.baselibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.network.LoadMoreStatus
import com.lz.baselibrary.network.NetworkStatus
import com.lz.baselibrary.network.RefreshStatus
import com.lz.baselibrary.repository.ArticleRepository

/**
 * PagingViewModel
 * @author linzheng
 */
class PagingViewModel(
        private val mArticleRepository: ArticleRepository
) : ViewModel() {

    val subscriptionId: MutableLiveData<Int> = MutableLiveData()

    private val pagingData = Transformations.map(subscriptionId) { mArticleRepository.getArticleList(it) }

    val articleList: LiveData<PagedList<Article>> = Transformations.switchMap(pagingData) {
        it.pagedList
    }

    val refreshStatus: LiveData<RefreshStatus> = Transformations.switchMap(pagingData) { it.refreshStatus }
    val loadMoreStatus: LiveData<LoadMoreStatus> = Transformations.switchMap(pagingData) { it.loadMoreStatus }
    val networkStatus: LiveData<NetworkStatus> = Transformations.switchMap(pagingData) { it.networkStatus }

    fun refresh() {
        pagingData.value?.refresh?.invoke()
    }

}