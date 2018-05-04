package com.xiuyuan.vnews.base

/**
 * @author Created by xiuyaun
 * @time on 2018/4/24
 */
interface BaseCallBack<T> {
    fun onSuccess(callback : T?)
    fun onFailed(message : String?)
    fun onFinshed()
}