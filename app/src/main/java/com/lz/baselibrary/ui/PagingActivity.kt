package com.lz.baselibrary.ui

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
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
import com.lz.baselibrary.view.loadmore.paging.MultiTypePagedListAdapter
import com.lz.baselibrary.view.loadmore.paging.MultiTypePagedListAdapterWrapper
import com.lz.baselibrary.viewmodel.ArticleViewModel
import com.lz.baselibrary.viewmodel.PagingViewModelFactory
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : LibraryBaseListActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this, PagingViewModelFactory())[ArticleViewModel::class.java]
    }

    //todo 存在问题，断网后下拉刷新会报错,报错原因在 getItemCount() 方法中
    private val mAdapterWrapper by lazy {
        MultiTypePagedListAdapterWrapper(mAdapter)
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
            mAdapterWrapper.normal()
            mViewModel.refresh()
        }
    }

    private fun bindViewModel() {
        mViewModel.pagedList.observe(this, Observer {
            //            Timber.d("PagedList Changed!")
//            try {
            mAdapterWrapper.submitList(it)
//            }catch (ex: Exception){
//                print("1111")
//            }
        })
        mViewModel.networkStatus.observe(this, Observer {
            it.bind(this)
        })
        mViewModel.refreshStatus.observe(this, Observer {
            it.bind(this)
        })
        mViewModel.loadMoreStatus.observe(this, Observer {
            it.bind(this)
        })
    }

    override fun retry() {
        super.retry()
        Handler().postDelayed(2000) {
            mViewModel.retry()
        }
    }

}
