package com.lz.baselibrary

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.net.toUri
import androidx.core.os.postDelayed
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.api.ApiConsumer
import com.lz.baselibrary.api.ApiFunction
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.model.wanandroid.SubscriptionArticle
import com.lz.baselibrary.multitype.SubscriptionArticleItemViewBinder
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.utils.PageFunction
import com.lz.baselibrary.utils.rxjava.PageTransformer
import com.lz.baselibrary.view.itemdecoration.VerticalItemDecoration
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreAdapterWrapper
import com.lz.baselibrary.view.recyclerview.RecyclerViewItemClickListener
import com.lz.baselibrary.view.recyclerview.SimpleOnItemClickListener
import com.lz.baselibrary.viewmodel.ListViewModel
import com.lz.baselibrary.viewmodel.ListViewModelFactory
import com.uber.autodispose.autoDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_list.*
import java.util.concurrent.TimeUnit

/**
 * @author linzheng
 */
class ListActivity : LibraryBaseListActivity<ListViewModel>(), LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private val mAdapterWrapper: LoadMoreAdapterWrapper by lazy {
        LoadMoreAdapterWrapper(mAdapter, this)
    }

    override val mViewModel: ListViewModel
        get() = ViewModelProviders.of(this, ListViewModelFactory()).get(ListViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(VerticalItemDecoration(0.5.dp2px(this), Color.parseColor("#e0e0e0")) { _, position ->
            position != mViewModel.mItems.size
        })
        rv_list.adapter = mAdapterWrapper
        rv_list.addOnItemTouchListener(RecyclerViewItemClickListener(rv_list, object : SimpleOnItemClickListener() {
            override fun onItemClick(view: View, position: Int) {
                val article = mViewModel.mItems[position] as SubscriptionArticle
                startActivity(Intent(Intent.ACTION_VIEW, article.link.toUri()))
            }
        }))

        srl_list.setOnRefreshListener(this)

        mRefresh = srl_list
        mLoadMore = mAdapterWrapper

        mAdapter.register(SubscriptionArticle::class, SubscriptionArticleItemViewBinder())
        showLoading()
        loadData()
    }


    private fun loadData() {
        //todo 已 observerOn 为分界，分别实现两个 Transformer 一个用来判断服务器获取的数据，一个用来调用 ListView 的方法
        Api.createApi(WanAndroidApi::class).getSubscriptionList(mViewModel.mPage, 408)
                .delay(1, TimeUnit.SECONDS)
                .map(ApiFunction())
                .map(PageFunction())
                .observeOn(mainThreadScheduler)
                .compose(PageTransformer(this))
                .autoDisposable(mScopeProvider)
                .subscribe(Consumer {
                    mViewModel.mItems.addAll(it)
                    mAdapterWrapper.notifyDataSetChanged()
                }, ApiConsumer(this))

    }

    override fun onRefresh() {
        mViewModel.refresh()
        loadData()
    }


    override fun loadMore(view: View) {
        mViewModel.loadMore()
        loadData()
    }

    override fun run() {
        super.run()
        loadData()
    }

}
