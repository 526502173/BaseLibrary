package com.lz.baselibrary.utils

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.EditText

/**
 * Uitls 大炸毁
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


    /**
     * 重启 App
     */
    fun relaunchApp(app: Application) = app.apply {
        val componentName = packageManager.getLaunchIntentForPackage(packageName).component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        app.startActivity(mainIntent)
        System.exit(0)
    }

    /**
     * 判断当前进程是否是主进程
     */
    fun isMainProcess(context: Context) = context.packageName == getCurrentProcessName(context)

    /**
     * 获取当前进程名称
     */
    private fun getCurrentProcessName(context: Context): String {
        val pid = android.os.Process.myPid()
        val activityManager = context.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.runningAppProcesses.filter { it.pid == pid }[0].processName
    }

    /**
     * 获取软件的版本号
     */
    fun getVersionCode(context: Context) = context.run {
        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).longVersionCode.toString()
    }

}
