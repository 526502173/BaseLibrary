package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.*
import kotlinx.android.synthetic.main.item_load_more.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class LoadMoreItemViewBinder(
        private val loadMoreListener: LoadMoreListener
) : ItemViewBinder<LoadMore, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = LoadMoreViewHolder(inflater.inflate(R.layout.item_load_more, parent, false))

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMore) {
        holder.itemView.apply {
            when (item.status) {
                LOAD_MORE_STATUS_LOAD_COMPLETE -> {
                    tv_hint.text = "加载完成"
                    pb_load_more.visibility = View.GONE
                }
                LOAD_MORE_STATUS_NOT_LOAD -> {
                    pb_load_more.visibility = View.VISIBLE
                    loadMoreListener.loadMore(this)
                    item.status = LOAD_MORE_STATUS_LOADING
                }
                LOAD_MORE_STATUS_NOT_MORE -> {
                    tv_hint.text = "没有更多了~"
                    pb_load_more.visibility = View.GONE
                }
                LOAD_MORE_STATUS_LOADING -> {

                }
            }
        }
    }


    class LoadMoreViewHolder(itemView: View) : BaseViewHolder(itemView)

}