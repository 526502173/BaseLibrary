package com.lz.baselibrary

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.multitype.ListItemViewBinder
import com.lz.baselibrary.multitype.LoadMoreDelegate
import com.lz.baselibrary.multitype.LoadMoreListener
import com.lz.baselibrary.utils.ToastUtils
import com.lz.baselibrary.view.RefreshListener
import com.lz.baselibrary.view.VerticalItemDecoration
import com.lz.baselibrary.viewmodel.ListViewModel
import com.lz.baselibrary.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_list.*
import me.drakeet.multitype.register

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/26
 * desc   : ListActivity
 * version: 1.0
</pre> *
 */
class ListActivity : LibraryBaseListActivity(), RefreshListener, LoadMoreListener {

    private val mLoadMoreDelegate = LoadMoreDelegate(this)

    override fun loadMore(view: View) {
        ToastUtils.showToast("加载更多")
        mHandler.sendEmptyMessageDelayed(1, 3000)
    }

    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            if (mPage == 5) {
                mLoadMoreDelegate.setLoading(true)
                return
            }
            mPage++
            repeat(10) {
                mListViewModel.mItems.add("123")
            }
            mAdapter.notifyDataSetChanged()
            mLoadMoreDelegate.setLoading(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        mListViewModel = ListViewModelFactory().create(ListViewModel::class.java)
        mAdapter.register(String::class, ListItemViewBinder())
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(VerticalItemDecoration(10, Color.BLACK))

        rv_list.adapter = mAdapter
        mLoadMoreDelegate.attach(rv_list)

        repeat(10) {
            mListViewModel.mItems.add("123")
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun refresh(isRefresh: Boolean) {

    }

}
