package com.lz.baselibrary.utils.initializer

import android.content.Context


/**
 * 初始化
 * @author linzheng
 */
interface Initialize {

    /**
     * 初始化操作
     */
    fun initial(context: Context)

    /**
     * 是否需要多进程初始化
     */
    fun isMultiProcessInitial(): Boolean

}