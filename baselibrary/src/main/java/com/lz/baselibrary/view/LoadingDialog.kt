package com.lz.baselibrary.view

import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseDialogFragment

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/24
 * desc   : LoadingDialog
 * version: 1.0
</pre> *
 */
class LoadingDialog : BaseDialogFragment(){

    override val layoutResID: Int
        get() = R.layout.dialog_loading

    override fun setParams() {
        setWidth(500)
        setHeight(500)
    }

    override fun initViewsAndEvents() {
    }

}
