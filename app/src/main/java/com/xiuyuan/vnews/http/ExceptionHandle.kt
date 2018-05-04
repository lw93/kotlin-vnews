package com.xiuyuan.vnews.http

import com.blankj.utilcode.util.NetworkUtils
import com.google.gson.JsonParseException
import com.xiuyuan.vnews.utils.VNewsUtil
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @author Created by xiuyaun
 * @time on 2018/5/1
 */
internal class ExceptionHandle {

    companion object {

        fun handleException(throwable: Throwable): String {
            if (!NetworkUtils.isConnected()) return VNewsUtil.UNCONNECT
            else if (throwable is ConnectException) return VNewsUtil.CONNECT
            else if (throwable is HttpException) return VNewsUtil.HTTP
            else if (throwable is TimeoutException) return VNewsUtil.TIME_OUT
            else if (throwable is UnknownError) return VNewsUtil.UNKNOWN
            else if (throwable is UnknownError) return VNewsUtil.UNKNOWN
            else if (throwable is UnknownHostException) return VNewsUtil.HTTP
            else if (throwable is JsonParseException) return VNewsUtil.PASER
            else return VNewsUtil.UNKNOWN
        }
    }

}