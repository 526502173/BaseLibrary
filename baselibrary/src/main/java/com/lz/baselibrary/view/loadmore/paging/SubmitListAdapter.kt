package com.lz.baselibrary.view.loadmore.paging

import androidx.paging.PagedList

/**
 * 因为 androidx 中提供的 PagedListAdapter 没有提供接口，
 * 所以这里自定义一个接口，只为方便扩展
 * @author linzheng
 */
interface SubmitListAdapter<T>{

    fun submitList(pagedList: PagedList<T>)

}