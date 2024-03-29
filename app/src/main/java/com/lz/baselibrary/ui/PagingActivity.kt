package com.lz.baselibrary.ui

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.os.postDelayed
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.bindUIStatus
import com.lz.baselibrary.bindPagedListAdapter
import com.lz.baselibrary.dp2px
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.ui.multitype.ArticleItemViewBinder
import com.lz.baselibrary.view.adapter.factory.AdapterFactory
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import com.lz.baselibrary.viewmodel.ArticlePagingViewModel
import com.lz.baselibrary.viewmodel.PagingViewModelFactory
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : LibraryBaseListActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this, PagingViewModelFactory())[ArticlePagingViewModel::class.java]
    }

    //Paging + MultiType
    private val mDiffAdapter by lazy {
        AdapterFactory.createDiffPagedListAdapter(adapter)
    }

    //Paging + MultiType + LoadMore
    private val mLoadMoreAdapterWrapper by lazy {
        AdapterFactory.createDiffPagedLoadMoreAdapter(adapter, mViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        refresh = srl_article
        loadMore = mLoadMoreAdapterWrapper
        showLoading()
        initRecyclerView()
        bindViewModel()
        mViewModel.subscriptionId.value = 408
    }


    /**
     * 初始化 RecyclerView
     */
    private fun initRecyclerView() {
        adapter.register(Article::class, ArticleItemViewBinder())
        rv_article.layoutManager = LinearLayoutManager(this)
        rv_article.addItemDecoration(BaseItemDecoration.createFromBottom(0.5.dp2px(this), Color.BLACK))
        rv_article.adapter = mLoadMoreAdapterWrapper
        srl_article.setOnRefreshListener(mViewModel)
    }

    private fun bindViewModel() {
        mViewModel.bindUIStatus(this)
        mViewModel.pagedList.bindPagedListAdapter(this, mLoadMoreAdapterWrapper)
    }


    override fun onRetry() {
        super.onRetry()
        Handler().postDelayed(2000) {
            mViewModel.onRetry()
        }
    }

}
