package com.xiuyuan.vnews.utils

import com.google.gson.GsonBuilder
import com.xiuyuan.vnews.http.VNewsHttp
import java.util.*

/**
 * @author Created by xiuyaun
 * @time on 2018/4/20
 */
internal class GsonUtil {

    companion object {
       private val TAG = VNewsHttp::class.java.simpleName
       private val builder = GsonBuilder().serializeNulls().create()
        fun toJson(obj:Any):String {
            return builder.toJson(obj)
        }
        fun <T> fromJson(json:String,type:Class<T>): T {
            return builder.fromJson(json,type)
        }
    }
}