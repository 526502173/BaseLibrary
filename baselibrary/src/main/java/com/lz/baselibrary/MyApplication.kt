package com.lz.baselibrary

import android.app.Application

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/07
 * desc   : Application
 * version: 1.0
</pre> *
 */
object  MyApplication : Application(){

    val mInstance: MyApplication by lazy({this})

}
