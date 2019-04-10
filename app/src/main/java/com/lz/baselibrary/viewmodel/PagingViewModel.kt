package com.lz.baselibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.repository.ArticleRepository

/**
 * @author linzheng
 */
class PagingViewModel(
        private val mArticleRepository: ArticleRepository
) : ViewModel(){

    val mSubscriptionId: MutableLiveData<Int> = MutableLiveData()

    val mArticleList: LiveData<PagedList<Article>> = Transformations.switchMap(mSubscriptionId){
        mArticleRepository.getArticleList(it)
    }

}