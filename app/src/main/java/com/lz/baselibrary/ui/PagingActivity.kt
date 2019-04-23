package com.lz.baselibrary.ui

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.bind
import com.lz.baselibrary.dp2px
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.ui.multitype.ArticleItemViewBinder
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import com.lz.baselibrary.view.loadmore.paging.MultiTypePagedListAdapterWrapper
import com.lz.baselibrary.viewmodel.ArticleViewModel
import com.lz.baselibrary.viewmodel.PagingViewModelFactory
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : LibraryBaseListActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this, PagingViewModelFactory())[ArticleViewModel::class.java]
    }

    private val mAdapterWrapper by lazy {
        MultiTypePagedListAdapterWrapper(mAdapter)
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
        rv_article.layoutManager = LinearLayoutManager(this)
        rv_article.addItemDecoration(BaseItemDecoration.createFromBottom(0.5.dp2px(this), Color.BLACK))
        rv_article.adapter = mAdapterWrapper
        srl_article.setOnRefreshListener {
            mAdapterWrapper.normal()
            mViewModel.refresh()
        }
    }

    private fun bindViewModel() {
        mViewModel.pagedList.observe(this, Observer { mAdapterWrapper.submitList(it) })
        mViewModel.networkStatus.observe(this, Observer { it.bind(this) })
        mViewModel.refreshStatus.observe(this, Observer { it.bind(this) })
        mViewModel.loadMoreStatus.observe(this, Observer { it.bind(this) })
    }
}
