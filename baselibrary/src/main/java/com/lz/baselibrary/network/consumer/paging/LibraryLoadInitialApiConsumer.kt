package com.lz.baselibrary.network.consumer.paging

import com.billy.android.loading.Gloading
import com.lz.baselibrary.EmptyDataException
import com.lz.baselibrary.network.consumer.LibraryApiConsumer
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.UIStatusData


/**
 * LibraryPagingApiConsumer
 * @author linzheng
 */
open class LibraryLoadInitialApiConsumer(
        private val uiStatusData: UIStatusData
) : LibraryApiConsumer(uiStatusData.networkStatus) {
    override fun accept(t: Throwable) {
        //重置状态
        uiStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_DISABLE)
        when (t) {
            is EmptyDataException -> {
                postFailedUIStatus(Gloading.STATUS_EMPTY_DATA)
            }
            else -> super.accept(t)
        }
    }

}