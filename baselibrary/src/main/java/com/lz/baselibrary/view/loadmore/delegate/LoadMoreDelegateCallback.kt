package com.lz.baselibrary.view.loadmore.delegate

/**
 * LoadMoreDelegate 的回调
 * @author linzheng
 */
interface LoadMoreDelegateCallback{

    fun getDataItem(position: Int) : Any?

    fun getDataItemCount(): Int

}