package com.lz.baselibrary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.R
import com.lz.baselibrary.ui.adapter.ArticleListAdapter
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
        mAdapter = ArticleListAdapter()
        rv_article.layoutManager = LinearLayoutManager(this)
        rv_article.adapter = mAdapter
        mViewModel.mArticleList.observe(this, Observer {
            mAdapter.submitList(it)
        })
        mViewModel.mSubscriptionId.value = 408
    }
}
