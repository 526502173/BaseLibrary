package com.lz.baselibrary.api

import io.reactivex.functions.Function

/**
 * @author linzheng
 */
class ApiFunction<T> : Function<Response<T>, T> {

    override fun apply(t: Response<T>): T {
        return t.data
    }

}