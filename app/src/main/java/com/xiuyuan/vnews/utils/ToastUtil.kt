package com.xiuyuan.vnews.utils

import android.content.Context
import com.blankj.utilcode.util.ToastUtils.setGravity
import android.widget.Toast
import com.xiuyuan.vnews.base.VNewsApplication


/**
 * @author Created by xiuyaun
 * @time on 2018/5/4
 */
object ToastUtil {

    var mToast: Toast? = null

    /**
     * 弹出Toast
     * @param context 上下文对象
     * @param text    提示的文本
     * @param duration 持续时间（0：短；1：长）
     */
    fun showLong(text: String) {
        if (mToast == null) {
            mToast = Toast.makeText(VNewsApplication.vNewsContext, text,  Toast.LENGTH_LONG)
        } else {
            mToast?.setText(text) }
        mToast?.show()
    }

    /**
     *
     * 弹出Toast
     * @param context 上下文对象
     * @param text    提示的文本
     * @param duration 持续时间（0：短；1：长）
     * @param gravity  位置（Gravity.CENTER;Gravity.TOP;...）
     */
    fun showShort(text: String) {
        if (mToast == null) {
            mToast = Toast.makeText(VNewsApplication.vNewsContext, text, Toast.LENGTH_SHORT)
        } else {
            mToast?.setText(text)
        }
        mToast?.show()
    }

    /**
     * 关闭Toast
     */
    fun cancelToast() {
        if (mToast != null) {
            mToast?.cancel()
        }
    }
}