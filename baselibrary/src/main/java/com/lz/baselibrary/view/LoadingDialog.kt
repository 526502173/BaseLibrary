package com.lz.baselibrary.view

import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseDialogFragment

/**
 * @author linzheng
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
