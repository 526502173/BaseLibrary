package com.lz.baselibrary.view.loadmore

import android.content.Context
import android.view.View
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore

/**
 * LoadMoreItemView
 * 用于 [LoadMoreItemViewBinder.LoadMoreViewHolder] 的 itemView
 * @author linzheng
 */
abstract class LoadMoreAdapter(
        open val mContext: Context
) : LoadMore {

    /**
     * LoadMoreViewHolder 的 itemView
     */
    abstract val mItemView: View

}