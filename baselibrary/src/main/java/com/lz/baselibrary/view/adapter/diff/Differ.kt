package com.lz.baselibrary.view.adapter.diff

import com.lz.baselibrary.view.adapter.submit.SubmitDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * 对 AsyncListDiffer 和 AsyncPagedListDiffer 的抽象
 * @author linzheng
 */
interface Differ<T> : SubmitDelegate<T> {

    val currentList: List<Any>

    val adapter: MultiTypeAdapter

    fun getItem(position: Int): Any?

    fun getItemCount(): Int

}