package com.lz.baselibrary.view.loadmore.adapter

import android.content.Context
import android.view.View
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore

/**
 * LoadMoreItemView
 * @author linzheng
 */
abstract class LoadMoreAdapter(
        open val context: Context,
        open val retry: () -> Unit
) : LoadMore {

    /**
     * LoadMoreViewHolder 的 itemView
     */
    abstract val mItemView: View

}