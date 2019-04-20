package com.lz.baselibrary.api

import com.lz.baselibrary.network.PagingLiveData
import io.reactivex.functions.Consumer

/**
 * 用于处理 Api 调用的异常
 * @author linzheng
 */
class ApiConsumer(liveData: PagingLiveData):Consumer<Throwable>{
    override fun accept(t: Throwable?) {

    }
}