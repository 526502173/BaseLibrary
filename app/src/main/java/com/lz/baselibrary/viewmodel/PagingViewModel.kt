package com.lz.baselibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.repository.ArticleRepository
import com.lz.baselibrary.repository.NetworkState

/**
 * @author linzheng
 */
class PagingViewModel(
        private val mArticleRepository: ArticleRepository
) : ViewModel() {

    val mSubscriptionId: MutableLiveData<Int> = MutableLiveData()

    private val mListing = Transformations.map(mSubscriptionId) { mArticleRepository.getArticleList(it) }

    val mArticleList: LiveData<PagedList<Article>> = Transformations.switchMap(mListing) {
        it.pagedList
    }

    val mRefreshState: LiveData<NetworkState> = Transformations.switchMap(mListing) { it.refreshSate }
    val mNetworkState: LiveData<NetworkState> = Transformations.switchMap(mListing) { it.networkState }

    fun refresh() {
        mListing.value?.refresh?.invoke()
    }

}