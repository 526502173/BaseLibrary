package com.lz.baselibrary.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lz.baselibrary.base.viewmodel.LibraryBaseListViewModel
import com.lz.baselibrary.repository.ArticleRepository

/**
 * @author linzheng
 */
class ArticleListViewModel(
        private val repository: ArticleRepository
) : LibraryBaseListViewModel() {

    //todo 暂时想出这么一个损招，一定有更好的解决方案(对一定有)
    fun bindPage(lifecycleOwner: LifecycleOwner) {
        page.observe(lifecycleOwner, Observer {
            repository.getArticleList(408, it, listData.value!!)
        })
    }

}
