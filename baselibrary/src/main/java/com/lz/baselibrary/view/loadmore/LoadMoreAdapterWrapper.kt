package com.lz.baselibrary.view.loadmore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * 基于 MultiTypeAdapter 实现的加载跟多
 * @author linzheng
 */
//todo 这个的 LoadMore 机制要改，原因是 LoadMore 的机制根据 Paging 的机制进行了比较大的修改
class LoadMoreAdapterWrapper(
        wrapperAdapter: MultiTypeAdapter,
        mListener: LoadMoreListener
) : MultiTypeAdapter() {

    /**
     * Delegate 对象
     */
    private val mDelegate: LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate.create(
            wrapperAdapter, LibraryLoadMoreInitialize.sLoadMoreAdapterFactory, mListener
    )

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