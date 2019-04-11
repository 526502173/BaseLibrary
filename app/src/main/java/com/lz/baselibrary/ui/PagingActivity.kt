package com.lz.baselibrary.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.R
import com.lz.baselibrary.dp2px
import com.lz.baselibrary.repository.NetworkState
import com.lz.baselibrary.ui.adapter.ArticleListAdapter
import com.lz.baselibrary.view.itemdecoration.VerticalItemDecoration
import com.lz.baselibrary.viewmodel.PagingViewModel
import com.lz.baselibrary.viewmodel.PagingViewModelFactory
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : AppCompatActivity() {

    lateinit var mViewModel: PagingViewModel

    lateinit var mAdapter: ArticleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        mViewModel = ViewModelProviders.of(this, PagingViewModelFactory())[PagingViewModel::class.java]
        initRecyclerView()
        bindViewModel()
        mViewModel.mSubscriptionId.value = 408
    }


    /**
     * 初始化 RecyclerView
     */
    private fun initRecyclerView() {
        mAdapter = ArticleListAdapter()
        rv_article.layoutManager = LinearLayoutManager(this)
        rv_article.addItemDecoration(VerticalItemDecoration(0.5.dp2px(this), Color.parseColor("#e0e0e0")) { _, position ->
            position != mViewModel.mArticleList.value?.size
        })
        rv_article.adapter = mAdapter
        srl_article.setOnRefreshListener {
            mViewModel.refresh()
        }
    }

    private fun bindViewModel() {
        mViewModel.mArticleList.observe(this, Observer {
            mAdapter.submitList(it)
        })
        mViewModel.mRefreshState.observe(this, Observer {
            srl_article.isRefreshing = it == NetworkState.LOADING
        })
        mViewModel.mNetworkState.observe(this, Observer {
            //网络出现故障
        })
    }

}
