package com.lz.baselibrary.utils

import android.widget.EditText

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/08
 * desc   : 普通的 Utils
 * version: 1.0
</pre> *
 */
object Utils {

    fun isEmpty(hintArray: Array<String>, vararg editTexts: EditText): Boolean {
        var result = true
        for (i in editTexts.indices) {
            if (editTexts[i].text.toString().isEmpty()) {
                result = false
                ToastUtils.showToast(hintArray[i])
                break
            }
        }
        return result
    }

    inline fun EditText.trimText() = text.trim()

}
