package com.lz.baselibrary.initializer

import android.content.Context
import com.lz.baselibrary.utils.initializer.LibraryGloadingInitialize
import com.lz.baselibrary.view.GloadingStatusLayout
import com.lz.baselibrary.view.LibraryGloadingStatusLayout

/**
 * [Gloading] 初始化
 * @author linzheng
 */
class GloadingInitialize : LibraryGloadingInitialize() {

    override fun createGloadingStatusLayout(context: Context, retry: Runnable): LibraryGloadingStatusLayout {
        return GloadingStatusLayout(context, retry)
    }

}