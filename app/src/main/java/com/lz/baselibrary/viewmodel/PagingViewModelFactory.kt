package com.lz.baselibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.repository.network.NetworkArticleRepository
import retrofit2.create

/**
 * @author linzheng
 */
class PagingViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = NetworkArticleRepository(Api.SYNC_RETROFIT.create())
        return ArticlePagingViewModel(repository) as T
    }

}