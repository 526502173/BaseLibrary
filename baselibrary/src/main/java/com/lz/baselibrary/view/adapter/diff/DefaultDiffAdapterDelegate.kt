package com.lz.baselibrary.view.adapter.diff

/**
 * @author linzheng
 */
class DefaultDiffAdapterDelegate<T>(
        private val differ: Differ<T>
) : DiffAdapterDelegate {
    override var items: List<Any>
        get() = differ.currentList
        set(value) {
            //不需要 set
        }

    override fun getItemCount(): Int {
        return differ.getItemCount()
    }

}