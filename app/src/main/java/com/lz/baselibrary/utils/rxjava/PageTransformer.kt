package com.lz.baselibrary.utils.rxjava

import com.lz.baselibrary.api.PageWrapper
import com.lz.baselibrary.api.RespWrapper
import com.lz.baselibrary.base.ListView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * 处理列表数据的 Transformer，配合 RxJava 一起使用
 * @author linzheng
 */
class PageTransformer<T>(
        private val listView: ListView,
        private val isRefresh: Boolean
) : ObservableTransformer<RespWrapper<PageWrapper<T>>, List<T>> {
    override fun apply(upstream: Observable<RespWrapper<PageWrapper<T>>>): Observable<List<T>> = upstream.doAfterNext {
        //判断是否加载完所有数据
    }.doOnNext {
        //只有成功才会调用
        listView.showSuccess()
    }.doFinally {
        //无论报错还是没有报错都要调用
        listView.refreshComplete()
        listView.loadMoreNormal()
    }.map {
        //剔除无用字段
        it.data.datas
    }

}