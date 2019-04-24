package com.lz.baselibrary.network

import androidx.lifecycle.MutableLiveData
import com.billy.android.loading.Gloading
import com.lz.baselibrary.view.globalstatus.LibraryGlobalStatusLayout
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * 接口的异常处理类
 * 用于和 RxJava 一起使用
 * @author linzheng
 */
open class LibraryApiConsumer(
        private val liveData: MutableLiveData<NetworkStatus>
) : Consumer<Throwable> {

    override fun accept(t: Throwable) {
        Timber.e(t)
        when (t) {
            is ConnectException, is UnknownHostException -> postFailedUIStatus(LibraryGlobalStatusLayout.GLOADING_STATUS_NETWORK_ERROR)
            is HttpException -> postFailedUIStatus(LibraryGlobalStatusLayout.GLOADING_STATUS_HTTP_ERROR)
            else -> if (!handleOrderException(t)) postFailedUIStatus(Gloading.STATUS_LOAD_FAILED)
        }
    }

    protected fun postFailedUIStatus(code: Int) {
        liveData.postValue(NetworkStatus.code(code))
    }

    /**
     * 处理其他异常
     * 返回 true 表示处理了这个异常，false 表示不处理，
     * 最终还是会调用 BaseView 的 showLoadFailed(LibraryGloadingStatusLayout.GLOADING_STATUS_ORDER_ERROR)
     */
    open fun handleOrderException(t: Throwable) = false

}