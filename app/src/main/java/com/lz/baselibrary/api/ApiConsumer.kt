package com.lz.baselibrary.api

import com.lz.baselibrary.network.DataSourceLiveData
import io.reactivex.functions.Consumer

/**
 * 用于处理 Api 调用的异常
 * @author linzheng
 */
class ApiConsumer(liveData: DataSourceLiveData):Consumer<Throwable>{
    override fun accept(t: Throwable?) {

    }
}