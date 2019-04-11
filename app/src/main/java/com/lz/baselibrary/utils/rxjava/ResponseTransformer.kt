package com.lz.baselibrary.utils.rxjava

import com.lz.baselibrary.api.RespWrapper
import com.lz.baselibrary.base.BaseView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * 处理非分页 Response 的 Transformer
 * @author linzheng
 */
class ResponseTransformer<T>(
        private val mBaseView: BaseView
) : ObservableTransformer<RespWrapper<T>, T> {
    override fun apply(upstream: Observable<RespWrapper<T>>): Observable<T> = upstream.doOnComplete {
        mBaseView.showSuccess()
    }.map {
        it.data
    }
}