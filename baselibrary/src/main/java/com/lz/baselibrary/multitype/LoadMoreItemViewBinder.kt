package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.utils.loadmore.LoadMoreItem
import com.lz.baselibrary.utils.loadmore.LoadMoreListener
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class LoadMoreItemViewBinder : ItemViewBinder<LoadMoreItem, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreItem, payloads: List<Any>) {
        super.onBindViewHolder(holder, item, payloads)
    }

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreItem) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder = LoadMoreViewHolder(inflater.inflate(R.layout.item_load_more, parent, false))

    class LoadMoreViewHolder(itemView: View) : BaseViewHolder<LoadMoreItem>(itemView) {

        private val pbLoading: ProgressBar = itemView.findViewById(R.id.pb_loading)

        private val tvLoading: TextView = itemView.findViewById(R.id.tv_loading)

        override fun bind(item: LoadMoreItem) {
            if (item.status == LoadMoreItem.LOAD_MORE_STATUS_NORMAL) {
                item.status = LoadMoreItem.LOAD_MORE_STATUS_LOADING
                pbLoading.visibility = View.VISIBLE
                tvLoading.text = "加载中..."
                sLoadMoreListener.loadMore(itemView)
            } else if (item.status == LoadMoreItem.LOAD_MORE_STATUS_NO_MORE) {
                pbLoading.visibility = View.GONE
                tvLoading.text = "没有更多了..."
            }
        }
    }

    companion object {

        lateinit var sLoadMoreListener: LoadMoreListener

    }

}