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
        private val mUIStatusData: UIStatusData
) : LibraryApiConsumer(mUIStatusData.networkStatus) {

    override fun accept(t: Throwable) {
        //如果出现异常，需要将 LoadMore 功能禁用，不然重新后去数据后，会出现 LoadMore 先触发的情况
        //只用在第一次加载的时候调用即可
        //todo 在触发异常后的LoadMore在下拉刷新会崩溃，老 BUG
        mUIStatusData.postLoadMoreStatus(LoadMoreStatus.LOAD_MORE_DISABLE)
        when (t) {
            is EmptyDataException -> {
                postFailedUIStatus(Gloading.STATUS_EMPTY_DATA)
            }
            else -> super.accept(t)
        }
    }

}