package com.lz.baselibrary

import android.app.Application
import com.heyooo.heymail.utils.initializer.Initializer
import com.lz.baselibrary.utils.exception.LibraryAppInstanceNullException

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

        fun app() = if (LibraryApplication.sInstance != null)
            LibraryApplication.sInstance
        else throw LibraryAppInstanceNullException()
    }

}
