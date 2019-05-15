package com.lz.baselibrary.view.adapter.factory

import com.lz.baselibrary.view.adapter.DiffPagedListAdapter
import com.lz.baselibrary.view.adapter.diff.PagedListDiffer

/**
 * @author linzheng
 */
class DiffPagedListAdapterFactory : AbstractAdapterFactory<DiffPagedListAdapter>() {
    override fun createAdapter(config: AdapterConfig): DiffPagedListAdapter {
        val differ = PagedListDiffer()
        val adapter = DiffPagedListAdapter(config.wrapperAdapter, differ)
        differ.adapter = adapter
        return adapter
    }

}