package com.lz.baselibrary

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lz.baselibrary.api.WanAndroidApi
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.model.wanandroid.SubscriptionArticle
import com.lz.baselibrary.multitype.SubscriptionArticleItemViewBinder
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.viewmodel.ListViewModel
import com.lz.baselibrary.viewmodel.ListViewModelFactory
import com.uber.autodispose.autoDisposable
import kotlinx.android.synthetic.main.activity_list.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author linzheng
 */
class ListActivity : LibraryBaseListActivity<ListViewModel>(), LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    override val mViewModel: ListViewModel
        get() = ViewModelProviders.of(this, ListViewModelFactory()).get(ListViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_list.adapter = mAdapter

        srl_list.setOnRefreshListener(this)

        mAdapter.register(SubscriptionArticle::class, SubscriptionArticleItemViewBinder())
        mAdapter.isEnableLoadMore = true
        mHolder.showLoading()
        loadData()
    }


    private fun loadData(isRefresh: Boolean = true) {
        Api.createApi(WanAndroidApi::class)
                .getSubscriptionList(mViewModel.mPage, 408)
                .delay(1, TimeUnit.SECONDS)
                .observeOn(mainThreadScheduler)
                .doFinally {
                    mHolder.showLoadSuccess()
                    srl_list.isRefreshing = false
                }
                .autoDisposable(mScopeProvider)
                .subscribe({
                    if (isRefresh) mViewModel.mItems.clear()
                    mViewModel.mItems.addAll(it.data.datas)
                    mAdapter.notifyDataSetChanged()
                }, {
                    Timber.e(it)
                })

    }

    override fun onRefresh() {
        mViewModel.mPage = 1
        loadData()
    }


    override fun loadMore(view: View) {
        mViewModel.mPage++
        loadData(false)
    }

}
