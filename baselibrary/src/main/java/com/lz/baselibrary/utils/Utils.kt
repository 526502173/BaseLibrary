package com.lz.baselibrary.utils

import android.widget.EditText

/**
 * @author linzheng
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
