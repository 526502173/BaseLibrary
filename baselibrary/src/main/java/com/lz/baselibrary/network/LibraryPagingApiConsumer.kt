package com.lz.baselibrary.network

import com.billy.android.loading.Gloading
import com.lz.baselibrary.EmptyDataException


/**
 * LibraryPagingApiConsumer
 * @author linzheng
 */
open class LibraryPagingApiConsumer(
        liveData: PagingLiveData
) : LibraryApiConsumer(liveData.networkStatus) {
    override fun accept(t: Throwable) {
        when (t) {
            is EmptyDataException -> {
                postNetworkFailed(Gloading.STATUS_EMPTY_DATA)
            }
            else -> super.accept(t)
        }
    }

}