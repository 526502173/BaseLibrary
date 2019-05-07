package com.lz.baselibrary.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.bind
import com.lz.baselibrary.dp2px
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.ui.multitype.ArticleItemViewBinder
import com.lz.baselibrary.view.adapter.DiffListAdapter
import com.lz.baselibrary.view.adapter.loadmore.DiffListLoadMoreAdapter
import com.lz.baselibrary.view.adapter.loadmore.LoadMoreAdapter
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import com.lz.baselibrary.view.recyclerview.RecyclerViewItemClickListener
import com.lz.baselibrary.view.recyclerview.SimpleOnItemClickListener
import com.lz.baselibrary.viewmodel.ArticleListViewModel
import com.lz.baselibrary.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_list.*
import timber.log.Timber

/**
 * @author linzheng
 */
class ListActivity : LibraryBaseListActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this, ListViewModelFactory())[ArticleListViewModel::class.java]
    }

    //Diff + MultiType
    private val mAdapterWrapper: DiffListAdapter by lazy {
        DiffListAdapter(mAdapter)
    }

    //LoadMore + MultiType
    private val mLoadMoreAdapterWrapper: LoadMoreAdapter by lazy {
        LoadMoreAdapter(mAdapter, mViewModel)
    }

    private val mDiffLoadMoreAdapterWrapper: DiffListLoadMoreAdapter by lazy {
        DiffListLoadMoreAdapter(mAdapter, mViewModel) {
            //retry
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //测试用的
        val realAdapter = mLoadMoreAdapterWrapper

        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(BaseItemDecoration.createFromBottom(0.5.dp2px(this), Color.parseColor("#e0e0e0")) { _, position ->
            position != realAdapter.items.size
        })
        rv_list.adapter = realAdapter
        rv_list.addOnItemTouchListener(RecyclerViewItemClickListener(rv_list, object : SimpleOnItemClickListener() {
            override fun onItemClick(view: View, position: Int) {
                val article = realAdapter.items[position] as Article
                startActivity(Intent(Intent.ACTION_VIEW, article.link.toUri()))
            }
        }))
        srl_list.setOnRefreshListener(mViewModel)

        mRefresh = srl_list
        mLoadMore = realAdapter
        mAdapter.register(Article::class, ArticleItemViewBinder())
        showLoading()
        bindViewModel()
        mViewModel.bindPage(this)
        mViewModel.page.value = 1
    }

    private fun bindViewModel() {
        mViewModel.list.observe(this, Observer {
            mLoadMoreAdapterWrapper.items = it
            mLoadMoreAdapterWrapper.notifyDataSetChanged()
//            mDiffLoadMoreAdapterWrapper.submitList(it)
        })
        mViewModel.bind(this)
    }

}
