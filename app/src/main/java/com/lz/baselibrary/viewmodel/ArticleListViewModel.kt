package com.lz.baselibrary.viewmodel

import com.lz.baselibrary.base.viewmodel.LibraryBaseListViewModel
import com.lz.baselibrary.network.data.ListData
import com.lz.baselibrary.repository.ArticleRepository

/**
 * @author linzheng
 */
class ArticleListViewModel(
        private val repository: ArticleRepository
) : LibraryBaseListViewModel() {

    override fun loadPageData(page: Int, listData: ListData) {
        repository.getArticleList(408, page, listData)
    }

}
