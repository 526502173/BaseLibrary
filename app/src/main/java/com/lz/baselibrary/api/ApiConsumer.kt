package com.lz.baselibrary.api

import com.lz.baselibrary.base.BaseView
import com.lz.baselibrary.network.LibraryApiConsumer

/**
 * 用于处理 Api 调用的异常
 * @author linzheng
 */
class ApiConsumer(mBaseView: BaseView) : LibraryApiConsumer(mBaseView){

    override fun handleOrderException(t: Throwable) = false

}