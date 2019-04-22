package com.lz.baselibrary.ui

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.toLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.dp2px
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.network.UIStatus
import com.lz.baselibrary.toAnyType
import com.lz.baselibrary.ui.multitype.ArticleItemViewBinder
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import com.lz.baselibrary.view.loadmore.paging.MultiTypePagedListAdapterWrapper
import com.lz.baselibrary.viewmodel.ArticleViewModel
import com.lz.baselibrary.viewmodel.PagingViewModelFactory
import kotlinx.android.synthetic.main.activity_paging.*
import timber.log.Timber

class PagingActivity : LibraryBaseListActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this, PagingViewModelFactory())[ArticleViewModel::class.java]
    }

    private val mAdapterWrapper by lazy {
        MultiTypePagedListAdapterWrapper(mAdapter)
    }

    private val mTestLiveData = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

//        mRefresh = srl_article
//        mLoadMore = mAdapterWrapper
//        showLoading()
//        initRecyclerView()
//        bindViewModel()
//        mViewModel.subscriptionId.value = 408
        mTestLiveData.observe(this, Observer {
            Timber.d("TestLiveData => $it")
        })
        mTestLiveData.value = 233
    }

    override fun onStart() {
        super.onStart()
        Thread {
            mTestLiveData.postValue(1)
            Thread.sleep(1000)
            mTestLiveData.postValue(2)
            Thread.sleep(1000)
            mTestLiveData.postValue(3)
        }.start()
    }


    /**
     * 初始化 RecyclerView
     */
    private fun initRecyclerView() {
        mAdapter.register(Article::class, ArticleItemViewBinder())
        rv_article.layoutManager = LinearLayoutManager(this)
        rv_article.itemAnimator = null
        rv_article.addItemDecoration(BaseItemDecoration.createFromBottom(0.5.dp2px(this), Color.parseColor("#e0e0e0")) { _, position ->
            position != mViewModel.livePagedList.value?.size
        })
        rv_article.adapter = mAdapterWrapper
        srl_article.setOnRefreshListener {
            mAdapterWrapper.normal()
            mViewModel.refresh()
        }
    }

    private fun bindViewModel() {
        mViewModel.livePagedList.observe(this, Observer {
            mAdapterWrapper.submitList(it.toAnyType())
        })
        mViewModel.uiStatus.observe(this, Observer {
            Timber.d("UIStatus => $it")
            when (it) {
                UIStatus.REFRESHING -> refreshing()
                UIStatus.REFRESH_COMPLETE -> refreshComplete()
                UIStatus.LOAD_MORE_NORMAL -> loadMoreNormal()
                UIStatus.LOAD_MORE_NO_MORE -> loadMoreNoMore()
                UIStatus.SUCCESS -> showSuccess()
                UIStatus.LOADING -> showLoading()
                UIStatus.FAILED_LOAD -> showLoadFailed(1)
                UIStatus.FAILED_EMPTY_DATA -> showEmpty()
            }
        })
    }
}
