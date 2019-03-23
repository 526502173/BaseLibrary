package com.lz.baselibrary

import android.os.Bundle
import android.view.View
import androidx.core.view.postDelayed
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.multitype.ListItemViewBinder
import com.lz.baselibrary.utils.ToastUtils
import com.lz.baselibrary.utils.loadmore.LoadMoreAdapter
import com.lz.baselibrary.utils.loadmore.LoadMoreItems
import com.lz.baselibrary.utils.loadmore.LoadMoreListener
import com.lz.baselibrary.view.RefreshListener
import com.lz.baselibrary.viewmodel.ListViewModel
import com.lz.baselibrary.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_list.*

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/26
 * desc   : ListActivity
 * version: 1.0
</pre> *
 */
class ListActivity : LibraryBaseListActivity<ListViewModel>(), RefreshListener, LoadMoreListener {

    private val mLoadMoreItems by lazy {
        LoadMoreItems(mViewModel.mItems)
    }

    private val mLoadMoreAdapter by lazy {
        LoadMoreAdapter(this).apply {
            items = mLoadMoreItems
        }
    }

    override val mViewModel: ListViewModel
        get() = ViewModelProviders.of(this, ListViewModelFactory()).get(ListViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_list.adapter = mLoadMoreAdapter

        mLoadMoreAdapter.register(String::class, ListItemViewBinder())
        mLoadMoreAdapter.isEnableLoadMore = true

        mLoadMoreItems.addAll(createData())
        mLoadMoreAdapter.notifyDataSetChanged()
    }

    override fun refresh(isRefresh: Boolean) {
        srl_list.postDelayed(3000) {
            srl_list.isRefreshing = false
        }
    }

    var mLoadMoreCount = 0

    override fun loadMore(view: View) {
        ToastUtils.showToast("加载更多...")
        srl_list.postDelayed(3000) {
            if (mLoadMoreCount == 4) {
                mLoadMoreAdapter.noMore()
            } else {
                mLoadMoreItems.addAll(createData())
                mLoadMoreAdapter.notifyDataSetChanged()
                mLoadMoreCount += 1
            }
        }
    }

    private fun createData() = listOf("", "", "", "", "", "", "", "", "", "")
}
