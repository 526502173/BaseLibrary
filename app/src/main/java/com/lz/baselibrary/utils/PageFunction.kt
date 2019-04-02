package com.lz.baselibrary.utils

import com.lz.baselibrary.NoMoreException
import com.lz.baselibrary.api.Page
import io.reactivex.functions.Function

/**
 * 处理分页的 Function
 * 结合 RxJava map 操作符一起使用
 * @author linzheng
 */
class PageFunction<T> : Function<Page<T>, List<T>> {
    override fun apply(t: Page<T>): List<T> {
        return if (t.over) throw NoMoreException()
        else t.datas
    }
}
