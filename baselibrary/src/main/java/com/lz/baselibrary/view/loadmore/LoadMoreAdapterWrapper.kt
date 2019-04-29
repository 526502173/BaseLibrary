package com.lz.baselibrary.view.loadmore

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
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
        private val wrapperAdapter: MultiTypeAdapter,
        listener: LoadMoreListener
) : MultiTypeAdapter(), LoadMore {

    /**
     * Delegate 对象
     */
    private val mDelegate: LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate.create(
            wrapperAdapter, LibraryLoadMoreInitialize.sLoadMoreAdapterFactory, object : LoadMoreListener {
        override fun onLoadMore(view: View) {
            listener.onLoadMore(view)
            //这里必须使用 post() 方法来执行 loading() 方法，因为 onLoadMore() 方法是在 Adapter 的 onBinderViewHolder() 中执行的。
            //在 Adapter 的 onBinderViewHolder() 中不能调用 notify() 方法。
            view.post{
                loading()
            }
        }
    })

    override var items: List<Any>
        get() = wrapperAdapter.items
        set(value) {
            wrapperAdapter.items = value
        }

    override fun getItemViewType(position: Int) = mDelegate.getItemViewType(position)

    override fun getItemCount() = mDelegate.getItemCount()

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int) = mDelegate.onCreateViewHolder(parent, indexViewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) = mDelegate.onBindViewHolder(holder, position, payloads)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = mDelegate.onBindViewHolder(holder, position)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = mDelegate.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = mDelegate.onViewRecycled(holder)

    override fun noMore() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_NO_MORE)
    }

    override fun loading() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_LOADING)
    }

    override fun normal() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_NORMAL)
    }

    override fun fail(code: Int) {
        setLoadMoreItemStatus(LoadMoreStatus.code(code))
    }

    private fun setLoadMoreItemStatus(status: LoadMoreStatus) {
        mDelegate.loadMoreItem.bind(status)
        notifyItemChanged(if (itemCount > 0) itemCount - 1 else 0)
    }

}