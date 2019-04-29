package com.lz.baselibrary.ui

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.*
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.ui.multitype.ArticleItemViewBinder
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import com.lz.baselibrary.view.loadmore.paging.MultiTypePagedListAdapter
import com.lz.baselibrary.view.loadmore.paging.MultiTypePagedListAdapterWrapper
import com.lz.baselibrary.viewmodel.ArticlePagingViewModel
import com.lz.baselibrary.viewmodel.PagingViewModelFactory
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : LibraryBaseListActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this, PagingViewModelFactory())[ArticlePagingViewModel::class.java]
    }

    private val mAdapterWrapper by lazy {
        MultiTypePagedListAdapterWrapper(mAdapter) {
            mViewModel.retry()
        }
    }

    private val mPagedListAdapter by lazy {
        MultiTypePagedListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        mRefresh = srl_article
        mLoadMore = mAdapterWrapper
        showLoading()
        initRecyclerView()
        bindViewModel()
        mViewModel.subscriptionId.value = 408
    }


    /**
     * 初始化 RecyclerView
     */
    private fun initRecyclerView() {
        mAdapter.register(Article::class, ArticleItemViewBinder())
        mPagedListAdapter.register(Article::class, ArticleItemViewBinder())
        rv_article.layoutManager = LinearLayoutManager(this)
        rv_article.addItemDecoration(BaseItemDecoration.createFromBottom(0.5.dp2px(this), Color.BLACK))
        rv_article.adapter = mAdapterWrapper
        srl_article.setOnRefreshListener {
            mViewModel.refresh()
        }
    }

    private fun bindViewModel() {
        mViewModel.networkStatus.bindNetworkStatus(this)
        mViewModel.refreshStatus.bindRefreshStatus(this)
        mViewModel.loadMoreStatus.bindLoadMoreStatus(this)
        mViewModel.pagedList.bindPagedList(this, mAdapterWrapper)
    }

    override fun retry() {
        super.retry()
        Handler().postDelayed(2000) {
            mViewModel.retry()
        }
    }

}
