package com.lz.baselibrary.network

import com.lz.baselibrary.base.BaseView
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import java.net.ConnectException

/**
 * @author linzheng
 */
class ApiConsumer(private val mBaseView: BaseView) : Consumer<Throwable> {

    override fun accept(t: Throwable) {
        when(t){
            is ConnectException -> {
                mBaseView.showLoadFailed()
            }
            //处理 Http Code != 200
            is HttpException -> {
                val response = t.response()
                mBaseView.showLoadFailed()
            }
            else -> mBaseView.showLoadFailed()
        }
    }

}