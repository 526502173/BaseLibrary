package com.lz.baselibrary.view.loadmore.adapter

import android.content.Context
import android.view.View
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.loadmore.RetryListener

/**
 * LoadMoreItemView
 * @author linzheng
 */
abstract class LoadMoreItemViewAdapter(
         val context: Context,
         val retry: RetryListener?
) : LoadMore {

    /**
     * LoadMoreViewHolder 的 itemView
     */
    abstract val mItemView: View

}