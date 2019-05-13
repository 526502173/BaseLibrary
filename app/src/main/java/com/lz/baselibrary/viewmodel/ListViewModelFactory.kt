package com.lz.baselibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.repository.network.NetworkArticleRepository
import retrofit2.create

/**
 * @author linzheng
 */
class ListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = NetworkArticleRepository(Api.ASYNC_RETROFIT.create())
        return ArticleListViewModel(repository) as T
    }

}