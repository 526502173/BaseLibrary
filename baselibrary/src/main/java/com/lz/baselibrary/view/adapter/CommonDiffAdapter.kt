package com.lz.baselibrary.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.submit.SubmitDelegate
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class CommonDiffAdapter<T>(
        override val mWrapperAdapter: MultiTypeAdapter
) : BaseAdapterWrapper(mWrapperAdapter),SubmitDelegate<T> {

    abstract val mDiffer: Differ<T>

    override var items: List<Any>
        get() = mDiffer.currentList
        set(value) {
            //Diff 的 Adapter 不需要指定 items，只需要调用 submitList() 方法即可
        }

    override fun getItemCount(): Int {
        return mDiffer.getItemCount()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        val item = getItem(position) ?: throw NullPointerException()
        getOutBinderByViewHolder(holder).onBindViewHolder(holder, item, payloads)
    }

    /**
     * 根据 [RecyclerView.ViewHolder] 对象获取对应的 [ItemViewBinder] 对象
     */
    open fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        return types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, RecyclerView.ViewHolder>
    }

    //此方法只会在 Diff 中需要用到
    open fun getItem(position: Int) = mDiffer.getItem(position)


}