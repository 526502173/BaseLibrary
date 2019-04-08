package com.lz.baselibrary.utils.rxjava

import com.lz.baselibrary.api.Page
import com.lz.baselibrary.api.Response
import com.lz.baselibrary.base.ListView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * 处理列表数据的 Transformer，配合 RxJava 一起使用
 * @author linzheng
 */
class PageTransformer<T>(
        private val mListView: ListView
) : ObservableTransformer<Response<Page<T>>, List<T>> {
    override fun apply(upstream: Observable<Response<Page<T>>>): Observable<List<T>> = upstream.doAfterNext {
        //判断是否加载完所有数据
        if (it.data.over) mListView.loadMoreNoMore()
        else mListView.loadMoreNormal()
    }.doOnNext {
        //只有成功才会调用
        mListView.showSuccess()
    }.doFinally {
        //无论报错还是没有报错都要调用
        mListView.refreshComplete()
    }.map {
        //剔除无用字段
        it.data.datas
    }

}