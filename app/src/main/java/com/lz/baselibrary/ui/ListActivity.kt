package com.lz.baselibrary.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.*
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.ui.multitype.ArticleItemViewBinder
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreAdapterWrapper
import com.lz.baselibrary.view.recyclerview.RecyclerViewItemClickListener
import com.lz.baselibrary.view.recyclerview.SimpleOnItemClickListener
import com.lz.baselibrary.viewmodel.ArticleListViewModel
import com.lz.baselibrary.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_list.*

/**
 * @author linzheng
 */
class ListActivity : LibraryBaseListActivity() {

    private val mAdapterWrapper: LoadMoreAdapterWrapper by lazy {
        LoadMoreAdapterWrapper(mAdapter, mViewModel)
    }

    private val mViewModel by lazy {
        ViewModelProviders.of(this, ListViewModelFactory())[ArticleListViewModel::class.java]
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
            //todo 实现 Diff
            mAdapterWrapper.items = it
            mAdapterWrapper.notifyDataSetChanged()
        })
        //todo 添加 Extensions 的方法调用下3个方法
        mViewModel.refreshStatus.bindRefreshStatus(this)
        mViewModel.loadMoreStatus.bindLoadMoreStatus(this)
        mViewModel.networkStatus.bindNetworkStatus(this)
    }

}
