package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.heyooo.heymail.utils.initializer.Initialize
import timber.log.Timber

/**
 * Timer 的初始化操作
 * @author linzheng
 */
class LibraryTimberInitialize : Initialize {

    override fun isMultiProcessInitial() = false

    override fun initial(context: Context) {
        Timber.plant(createTree())
    }

    /**
     * 创建 Timber 的 Tree
     */
    open fun createTree() = createDebugTree()

    /**
     * 穿件 Timber 的 Debug Tree
     */
    open fun createDebugTree() = Timber.DebugTree()

}
