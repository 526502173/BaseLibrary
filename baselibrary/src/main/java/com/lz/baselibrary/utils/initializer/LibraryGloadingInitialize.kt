package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.billy.android.loading.Gloading
import com.lz.baselibrary.view.global.LibraryGlobalStatusLayout

/**
 * Gloading 初始化
 * @author linzheng
 */
open abstract class LibraryGloadingInitialize : SimpleInitialize() {

    override fun initial(context: Context) {
        Gloading.initDefault { holder, convertView, status ->
            var layout: LibraryGlobalStatusLayout? = null
            if (convertView != null && convertView is LibraryGlobalStatusLayout) {
                layout = convertView
            }
            if (layout == null) {
                layout = createGloadingStatusLayout(context, holder.retryTask)
            }
            layout.setStatus(status)
            layout
        }
    }

    /**
     * 创建 GloadingStatusLayout 对象
     */
    abstract fun createGloadingStatusLayout(context: Context, retry: Runnable): LibraryGlobalStatusLayout


}