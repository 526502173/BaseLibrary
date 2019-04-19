package com.lz.baselibrary.utils.initializer

import com.lz.baselibrary.network.ApiConfig
import com.lz.baselibrary.network.RetrofitConfig

/**
 * Api 初始化
 * @author linzheng
 */
class ApiInitialize : LibraryApiInitialize() {

    override fun createApiConfig(): ApiConfig {
        return ApiConfig.create(retrofitConfig = RetrofitConfig.create(baseUrl = "https://www.wanandroid.com"))
    }

}
