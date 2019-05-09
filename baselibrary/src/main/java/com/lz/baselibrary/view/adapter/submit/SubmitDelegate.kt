package com.lz.baselibrary.view.adapter.submit

/**
 * 对 Diff 中的 submit() 方法进行抽象，方便对自定义的 Adapter 添加 Diff 的实现
 * @author linzheng
 */
interface SubmitDelegate<T> {

    fun submitList(list: T)

    fun submitList(list: T, callback: Runnable)
}