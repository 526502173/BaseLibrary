package com.lz.baselibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lz.baselibrary.base.viewmodel.LibraryBaseListViewModel
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.repository.ArticleRepository

/**
 * @author linzheng
 */
class ArticleListViewModel(
        private val mArticleRepository: ArticleRepository
) : LibraryBaseListViewModel() {

    val subscriptionId: MutableLiveData<Int> = MutableLiveData()

    override val listData: LiveData<ListData> = Transformations.map(subscriptionId) {
        mArticleRepository.getArticleList(it)
    }

}