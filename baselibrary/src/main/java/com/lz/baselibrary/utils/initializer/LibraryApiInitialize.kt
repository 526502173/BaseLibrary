package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.network.ApiConfig

/**
 * Api 初始化器
 * 建议放在 SplashActivity 中执行
 * @author linzheng
 */
open class LibraryApiInitialize : SimpleInitialize() {

    override fun initial(context: Context) {
        Api.mApiConfig = createApiConfig()
    }

    /**
     * 创建 Api 配置对象
     */
    open fun createApiConfig() = ApiConfig()

}