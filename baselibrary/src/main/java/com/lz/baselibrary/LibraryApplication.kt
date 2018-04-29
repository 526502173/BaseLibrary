package com.lz.baselibrary

import android.app.Application

/**
 * @author linzheng
 */
open class LibraryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {

        private lateinit var mInstance: LibraryApplication

        fun getInstance() = mInstance
    }

}
