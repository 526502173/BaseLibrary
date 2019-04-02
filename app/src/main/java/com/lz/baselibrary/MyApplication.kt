package com.lz.baselibrary

import com.lz.baselibrary.utils.initializer.ApiInitialize
import com.lz.baselibrary.utils.initializer.GloadingInitialize
import com.lz.baselibrary.utils.initializer.Initializer
import com.lz.baselibrary.utils.initializer.LibraryTimberInitialize
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/27
 * desc   :
 * version: 1.0
</pre> *
 */
class MyApplication : LibraryApplication() {

    override fun onCreate() {
        Initializer.init(listOf(ApiInitialize(), LibraryTimberInitialize(), GloadingInitialize()))
        super.onCreate()
    }

}

val mainThreadScheduler: Scheduler = AndroidSchedulers.mainThread()
