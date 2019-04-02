package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.lz.baselibrary.view.GlobalStatusLayout
import com.lz.baselibrary.view.global.LibraryGlobalStatusLayout

/**
 * [Gloading] 初始化
 * @author linzheng
 */
class GloadingInitialize : LibraryGloadingInitialize() {

    override fun createGloadingStatusLayout(context: Context, retry: Runnable): LibraryGlobalStatusLayout {
        return GlobalStatusLayout(context, retry)
    }

}