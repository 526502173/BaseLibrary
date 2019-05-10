package com.lz.baselibrary.view.adapter.loadmore

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.adapter.CommonDiffAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
//todo 将 delegate 声明在 构造函数中，然后使用 by 关键字，减少方法的调用
abstract class CommonDiffLoadMoreAdapter<T>(
        override val wrapperAdapter: MultiTypeAdapter,
        open val listener: LoadMoreListener,
        open val retry: () -> Unit
) : CommonDiffAdapter<T>(wrapperAdapter), LoadMore {

    private val mDelegate: LoadMoreAdapterDelegate by lazy {
        DefaultLoadMoreAdapterDelegate.create(
                this,
                listener,
                this,
                retry
        )
    }

    override fun getItemCount(): Int {
        return mDelegate.getItemCount()
    }

    override fun getItemViewType(position: Int) = mDelegate.getItemViewType(position)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        mDelegate.onBindViewHolder(holder, position, payloads)
    }

    override fun noMore() {
        mDelegate.noMore()
    }

    override fun loading() {
        mDelegate.loading()
    }

    override fun disable() {
        mDelegate.disable()
    }

    override fun readly() {
        mDelegate.readly()
    }

    override fun fail(code: Int) {
        mDelegate.fail(code)
    }

    override fun submitList(list: T) {
        differ.submitList(list)
    }

    override fun submitList(list: T, callback: Runnable) {
        differ.submitList(list, callback)
    }

    companion object {
        fun create(
                wrapperAdapter: MultiTypeAdapter,
                listener: LoadMoreListener,
                retry: () -> Unit,
                delegate: LoadMoreAdapterDelegate
        ) {
        }
    }

}