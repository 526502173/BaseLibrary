package com.lz.baselibrary.view.adapter

import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.submit.SubmitDelegate
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
open class CommonDiffAdapter<T>(
        wrapperAdapter: MultiTypeAdapter,
        private val differ: Differ<T>
) : BaseAdapter(wrapperAdapter), SubmitDelegate<T> by differ {

    override var items: List<Any>
        get() = differ.currentList
        set(value) {
            //Diff 的 Adapter 不需要指定 items，只需要调用 submitList() 方法即可
        }

    override fun getItemCount(): Int {
        return differ.getItemCount()
    }

    override fun setLoadMoreListener(listener: LoadMoreListener) {
        //no thing...
    }

}