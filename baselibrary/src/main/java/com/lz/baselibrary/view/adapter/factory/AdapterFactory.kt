package com.lz.baselibrary.view.adapter.factory

import android.view.View
import androidx.paging.PagedList
import com.lz.baselibrary.view.adapter.CommonDiffAdapter
import com.lz.baselibrary.view.adapter.DiffListAdapter
import com.lz.baselibrary.view.adapter.DiffPagedListAdapter
import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.ListDiffer
import com.lz.baselibrary.view.adapter.diff.PagedListDiffer
import com.lz.baselibrary.view.adapter.loadmore.DiffListLoadMoreAdapter
import com.lz.baselibrary.view.adapter.loadmore.DiffPagedListLoadMoreAdapter
import com.lz.baselibrary.view.adapter.loadmore.LoadMoreAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
//todo 使用工厂模式优化此类代码,因为设计上的问题，用工厂模式优化存在难点，但是也是有可能实现的。
object AdapterFactory {

    fun createDiffListAdapter(wrapperAdapter: MultiTypeAdapter): DiffListAdapter {
        return create(wrapperAdapter, false)
    }

    fun createDiffPagedListAdapter(wrapperAdapter: MultiTypeAdapter): DiffPagedListAdapter {
        return create(wrapperAdapter, true)
    }

    fun createDiffListLoadMoreAdapter(
            wrapperAdapter: MultiTypeAdapter,
            listener: LoadMoreListener,
            retry: () -> Unit
    ): DiffListLoadMoreAdapter {
        return create(wrapperAdapter, false, retry, listener)
    }

    fun createDiffPagedLoadMoreAdapter(
            wrapperAdapter: MultiTypeAdapter,
            retry: () -> Unit
    ): DiffPagedListLoadMoreAdapter {
        //todo 消除这个空白的 LoadMoreListener
        return create(wrapperAdapter, true, retry, object : LoadMoreListener {
            override fun onLoadMore(view: View) {
                // DiffPagedList 不需要使用此回调
            }
        })
    }

    fun createLoadMoreAdapter(
            wrapperAdapter: MultiTypeAdapter,
            listener: LoadMoreListener,
            retry: () -> Unit
    ): LoadMoreAdapter {
        return create(wrapperAdapter, false, retry, listener)
    }

    fun <T> create(
            wrapperAdapter: MultiTypeAdapter,
            isPagedList: Boolean,
            retry: (() -> Unit)? = null,
            listener: LoadMoreListener? = null
    ): T {
        val differ = if (isPagedList) PagedListDiffer() else ListDiffer()
        val adapter = if (retry != null && listener != null) {
            val delegate: LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate.create(wrapperAdapter, listener, retry)
            val adapter = if (isPagedList) DiffPagedListLoadMoreAdapter(wrapperAdapter, delegate, differ as Differ<PagedList<Any>>)
            else DiffListLoadMoreAdapter(wrapperAdapter, delegate, differ as Differ<List<Any>>)
            delegate.callback = adapter
            adapter
        } else CommonDiffAdapter(wrapperAdapter, differ)
        differ.adapter = adapter
        return adapter as T
    }

}