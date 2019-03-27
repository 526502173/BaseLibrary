package com.lz.baselibrary.initializer

import com.lz.baselibrary.network.ApiConfig
import com.lz.baselibrary.network.RetrofitConfig
import com.lz.baselibrary.utils.initializer.LibraryApiInitialize

/**
 * Api 初始化
 * @author linzheng
 */
class ApiInitialize : LibraryApiInitialize() {

    override fun createApiConfig(): ApiConfig {
        return ApiConfig(retrofitConfig = RetrofitConfig(baseUrl = "https://www.wanandroid.com"))
    }

}
