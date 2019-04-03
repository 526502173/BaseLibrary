package com.lz.baselibrary.network

import com.billy.android.loading.Gloading
import com.lz.baselibrary.base.BaseView
import com.lz.baselibrary.view.globalstatus.LibraryGlobalStatusLayout
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import java.net.ConnectException

/**
 * 接口的异常处理类
 * 用于和 RxJava 一起使用
 * @author linzheng
 */
abstract class LibraryApiConsumer(private val mBaseView: BaseView) : Consumer<Throwable> {

    override fun accept(t: Throwable) {
        when (t) {
            is ConnectException -> mBaseView.showLoadFailed(LibraryGlobalStatusLayout.GLOADING_STATUS_NETWORK_ERROR)
            is HttpException -> mBaseView.showLoadFailed(LibraryGlobalStatusLayout.GLOADING_STATUS_HTTP_ERROR)
            else -> if (!handleOrderException(t)) mBaseView.showLoadFailed(Gloading.STATUS_LOAD_FAILED)
        }
    }

    /**
     * 处理其他异常
     * 返回 true 表示处理了这个异常，false 表示不处理，
     * 最终还是会调用 BaseView 的 showLoadFailed(LibraryGloadingStatusLayout.GLOADING_STATUS_ORDER_ERROR)
     */
    abstract fun handleOrderException(t: Throwable): Boolean

}