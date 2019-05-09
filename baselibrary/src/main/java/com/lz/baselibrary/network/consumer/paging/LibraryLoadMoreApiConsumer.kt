package com.lz.baselibrary.network.consumer.paging

import com.lz.baselibrary.network.status.UIStatusData
import io.reactivex.functions.Consumer

/**
 * @author linzheng
 */
//todo 需要添加扩展性
class LibraryLoadMoreApiConsumer(
        uiStatusData: UIStatusData,
        isInitial: Boolean = true,
        private val delegate: Consumer<Throwable> =
                if (isInitial) LibraryLoadInitialApiConsumer(uiStatusData)
                else LibraryLoadAfterApiConsumer(uiStatusData)
) : Consumer<Throwable> by delegate {

    constructor(uiStatusData: UIStatusData, page: Int) : this(
            isInitial = page == 1,
            uiStatusData = uiStatusData
    )

}