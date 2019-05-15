package com.lz.baselibrary.view.adapter.factory

import com.lz.baselibrary.view.adapter.DiffListAdapter
import com.lz.baselibrary.view.adapter.diff.ListDiffer

/**
 * @author linzheng
 */
class DiffListAdapterFactory : AbstractAdapterFactory<DiffListAdapter>() {
    override fun createAdapter(config: AdapterConfig): DiffListAdapter {
        val differ = ListDiffer()
        val adapter = DiffListAdapter(config.wrapperAdapter, differ)
        differ.adapter = adapter
        return adapter
    }
}