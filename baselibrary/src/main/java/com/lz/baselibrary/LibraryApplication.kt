package com.lz.baselibrary

import android.app.Application
import com.lz.baselibrary.utils.initializer.Initializer

/**
 * MyApplication 需要继承此类
 * @author linzheng
 */
open class LibraryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        Initializer.applicationInit(this)
    }

    companion object {

        private lateinit var sInstance: Application

        /**
         * 获取 Application 对象实例
         */
        fun app() = if (this::sInstance.isInitialized)
            LibraryApplication.sInstance
        else throw LibraryAppInstanceNullException()
    }

}
