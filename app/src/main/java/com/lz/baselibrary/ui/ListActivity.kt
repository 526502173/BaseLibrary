package com.lz.baselibrary.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.dp2px
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
class ListActivity : LibraryBaseListActivity(), LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private val mAdapterWrapper: LoadMoreAdapterWrapper by lazy {
        LoadMoreAdapterWrapper(mAdapter, this).apply {
        }
    }

    private val mViewModel by lazy {
        ViewModelProviders.of(this, ListViewModelFactory())[ArticleListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(BaseItemDecoration.createFromBottom(0.5.dp2px(this), Color.parseColor("#e0e0e0")) { _, position ->
            //            position != mViewModel.mItems.size
            true
        })
        rv_list.adapter = mAdapterWrapper
        rv_list.addOnItemTouchListener(RecyclerViewItemClickListener(rv_list, object : SimpleOnItemClickListener() {
            override fun onItemClick(view: View, position: Int) {
//                val article = mViewModel.mItems[position] as Article
//                startActivity(Intent(Intent.ACTION_VIEW, article.link.toUri()))
            }
        }))

        srl_list.setOnRefreshListener(this)

        mRefresh = srl_list
        mLoadMore = mAdapterWrapper
        mAdapter.register(Article::class, ArticleItemViewBinder())
        showLoading()
        loadData()
    }


    private fun loadData(isRefresh: Boolean = true) {
//        Api.createApi(WanAndroidApi::class).getSubscriptionList(mViewModel.mPage, 408)
//                .delay(2, TimeUnit.SECONDS)
//                .observeOn(mainThreadScheduler)
//                .compose(PageTransformer(this))
//                .autoDisposable(mScopeProvider)
//                .subscribe(Consumer {
//                    mAdapterWrapper.notifyDataSetChanged()
//                })
    }

    override fun onRefresh() {
        loadData()
    }


    override fun onLoadMore(view: View) {
        loadData(false)
//        view.postDelayed(3000) {
//            loadMoreNoMore()
//        }
    }

    override fun retry() {
        super.retry()
        loadData()
    }

}
