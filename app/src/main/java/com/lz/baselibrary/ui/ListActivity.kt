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
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import com.lz.baselibrary.view.loadmore.diff.DiffLoadMoreAdapterWrapper
import com.lz.baselibrary.view.recyclerview.RecyclerViewItemClickListener
import com.lz.baselibrary.view.recyclerview.SimpleOnItemClickListener
import com.lz.baselibrary.viewmodel.ArticleListViewModel
import com.lz.baselibrary.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_list.*

/**
 * @author linzheng
 */
class ListActivity : LibraryBaseListActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this, ListViewModelFactory())[ArticleListViewModel::class.java]
    }

    private val mAdapterWrapper: DiffLoadMoreAdapterWrapper by lazy {
        DiffLoadMoreAdapterWrapper(mAdapter, {
            //Retry
        }, mViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(BaseItemDecoration.createFromBottom(0.5.dp2px(this), Color.parseColor("#e0e0e0")) { _, position ->
            position != mAdapterWrapper.items.size
        })
        rv_list.adapter = mAdapterWrapper
        rv_list.addOnItemTouchListener(RecyclerViewItemClickListener(rv_list, object : SimpleOnItemClickListener() {
            override fun onItemClick(view: View, position: Int) {
                val article = mAdapterWrapper.items[position] as Article
                startActivity(Intent(Intent.ACTION_VIEW, article.link.toUri()))
            }
        }))

        srl_list.setOnRefreshListener(mViewModel)

        mRefresh = srl_list
        mLoadMore = mAdapterWrapper
        mAdapter.register(Article::class, ArticleItemViewBinder())
        showLoading()
        bindViewModel()
        mViewModel.bindPage(this)
        mViewModel.page.value = 1
    }

    private fun bindViewModel() {
        mViewModel.list.observe(this, Observer {
            mAdapterWrapper.submitList(it)
        })
        mViewModel.bind(this)
    }

}
