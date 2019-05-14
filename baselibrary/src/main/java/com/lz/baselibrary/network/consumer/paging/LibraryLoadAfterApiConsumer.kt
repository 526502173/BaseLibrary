package com.lz.baselibrary.network.consumer.paging

import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.UIStatusData
import com.lz.baselibrary.view.itemdecoration.loadmore.LOAD_MORE_FAIL_CODE_HTTP
import com.lz.baselibrary.view.itemdecoration.loadmore.LOAD_MORE_FAIL_CODE_NOT_NETWORK
import com.lz.baselibrary.view.itemdecoration.loadmore.LOAD_MORE_FAIL_CODE_OTHER
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException


/**
 * LibraryPagingApiConsumer
 * @author linzheng
 */
open class LibraryLoadAfterApiConsumer(
        private val mUIStatusData: UIStatusData
) : Consumer<Throwable> {
    override fun accept(t: Throwable) {
        val failCode = when (t) {
            is ConnectException, is UnknownHostException -> LOAD_MORE_FAIL_CODE_NOT_NETWORK
            is HttpException -> LOAD_MORE_FAIL_CODE_HTTP
            else -> LOAD_MORE_FAIL_CODE_OTHER
        }
        mUIStatusData.postLoadMoreStatus(LoadMoreStatus.code(failCode))
    }

}