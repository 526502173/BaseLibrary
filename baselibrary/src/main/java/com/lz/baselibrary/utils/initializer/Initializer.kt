package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.lz.baselibrary.utils.Utils
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 初始化器
 * 使用 RxJava 在子线程中完成初始化操作
 * @author linzheng
 */
object Initializer {

    private val mApplicationInitializeList = mutableListOf<Initialize>()

    private val mSplashInitializeList = mutableListOf<Initialize>()

    /**
     * 初始化 Initialize List
     * 此方法需要在 Application.onCreate() 中 super.onCreate() 之前调用
     */
    fun init(appInitList: List<Initialize> = listOf(), splashInitList: List<Initialize> = listOf()) {
        mApplicationInitializeList.clear()
        mSplashInitializeList.clear()
        mApplicationInitializeList.addAll(appInitList)
        mSplashInitializeList.addAll(splashInitList)
    }

    /**
     * 在 Application 中初始化
     * 因为这里采用的子线程初始化，所以不建议在 Application 中初始化重要数据
     */
    fun applicationInit(context: Context) {
        val list = if (Utils.isMainProcess(context)) {
            mApplicationInitializeList
        } else {
            mApplicationInitializeList.filter { it.isMultiProcessInitial() }
        }
        executeInit(context, list).subscribe()
    }

    /**
     * 在 SplashActivity 中初始化
     */
    fun splashInit(context: Context, onComplete: () -> Unit) {
        executeInit(context, mSplashInitializeList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete)
    }

    /**
     * 执行初始化
     */
    private fun executeInit(context: Context, initializerList: List<Initialize>) = Completable.create {
        try {
            initializerList.forEach {
                it.initial(context)
            }
            it.onComplete()
        } catch (ex: Exception) {
            it.onError(ex)
        }
    }.subscribeOn(Schedulers.io())

}