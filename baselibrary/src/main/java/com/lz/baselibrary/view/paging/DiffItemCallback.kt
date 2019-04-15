package com.lz.baselibrary.view.paging

/**
 * 因为 DiffUtil.ItemCallback 不是接口而是抽象类
 * 所以此类主要是将抽象类变成了接口
 * @author linzheng
 */
interface DiffItemCallback<T> {

    /**
     * 对应 DiffUtil.ItemCallback 的 areContentsTheSame 方法
     */
    fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    /**
     * 对应 DiffUtil.ItemCallback 的 areItemsTheSame 方法
     */
    fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    /**
     * 对应 DiffUtil.ItemCallback 的 getChangePayload 方法
     */
    fun getChangePayload(oldItem: T, newItem: T): Any?

}