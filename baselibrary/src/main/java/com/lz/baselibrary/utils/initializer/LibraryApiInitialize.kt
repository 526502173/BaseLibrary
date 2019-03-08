package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.heyooo.heymail.utils.initializer.Initialize
import com.lz.baselibrary.network.Api
import com.lz.baselibrary.network.ApiConfig

/**
 * Api 初始化器
 * 建议放在 SplashActivity 中执行
 * @author linzheng
 */
class LibraryApiInitialize : Initialize {

    override fun initial(context: Context) {
        Api.mApiConfig = createApiConfig()
    }

    override fun isMultiProcessInitial() = false

    /**
     * 创建 Api 配置对象
     */
    open fun createApiConfig() = ApiConfig()

}