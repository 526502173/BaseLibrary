package com.lz.baselibrary.utils

import android.widget.EditText

/**
 * Uitls
 * @author linzheng
 */
object Utils {


    /**
     * 检查 [editTexts] 是否为空，如果为空返回 false
     * 并且用 Toast 显示 [hintResIdArray] 中对应的字符串
     */
    fun isEmpty(hintResIdArray: Array<Int>, vararg editTexts: EditText): Boolean {
        var result = true
        for (i in editTexts.indices) {
            if (editTexts[i].text.toString().isEmpty()) {
                result = false
                ToastUtils.showToast(hintResIdArray[i])
                break
            }
        }
        return result
    }

    /**
     * 检查 [editTexts] 是否为空，如果为空返回 false
     * 并且用 Toast 显示 [hintResIdArray] 中对应的字符串
     */
    fun isEmpty(hintStringArray: Array<String>, vararg editTexts: EditText): Boolean {
        var result = true
        for (i in editTexts.indices) {
            if (editTexts[i].text.toString().isEmpty()) {
                result = false
                ToastUtils.showToast(hintStringArray[i])
                break
            }
        }
        return result
    }


}
