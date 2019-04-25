package com.lz.baselibrary.view.loadmore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * 基于 MultiTypeAdapter 实现的加载跟多
 * @author linzheng
 */
class LoadMoreAdapterWrapper(
        private val mWrapperAdapter: MultiTypeAdapter,
        mListener: LoadMoreListener
) : MultiTypeAdapter() {

    private val mDelegate: LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate.create(
            mWrapperAdapter, LibraryLoadMoreInitialize.sLoadMoreAdapterFactory, mListener
    )

    override var items: List<Any>
        get() = mWrapperAdapter.items
        set(value) {
            mWrapperAdapter.items = value
        }

    override var types: Types
        get() = mWrapperAdapter.types
        set(value) {
            mWrapperAdapter.types = value
        }

    override fun getItemViewType(position: Int) = mDelegate.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int) = mDelegate.onCreateViewHolder(parent, indexViewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) = mDelegate.onBindViewHolder(holder, position, payloads)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = mDelegate.onBindViewHolder(holder, position)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = mDelegate.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = mDelegate.onViewRecycled(holder)

    override fun getItemCount() = mDelegate.getItemCount()


}