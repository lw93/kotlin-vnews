package com.xiuyuan.vnews.http

import com.xiuyuan.vnews.api.VNewsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * @author Created by xiuyaun
 * @time on 2018/4/20
 */
open class VNewsHttp {

    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    private object SingleTon{
        val INSTANCE = VNewsHttp()
    }
    private val IP = arrayOf("0","1","2","3","5","7","9",".")
    private val INDEX = intArrayOf(3,6,7,1,0,5,7,2,3,2,7,1,2,4)
    private val url = createUrl()
    companion object {
        val TAG = VNewsHttp::class.java.simpleName
        private var CPU_COUNT = Runtime.getRuntime().availableProcessors()
        var pool = ThreadPoolExecutor(1, CPU_COUNT,
                1, TimeUnit.SECONDS, ArrayBlockingQueue(128))
        val INSTANCE: VNewsHttp = SingleTon.INSTANCE
    }

    constructor(){
     if (null == retrofit) {
            synchronized(VNewsHttp::class) {
                if (null == retrofit) {
                    okHttpClient = OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(15, TimeUnit.SECONDS)
                            .build()
                    retrofit = Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(okHttpClient)
                            .build()
                }
            }
        }
    }

    fun createApi(): VNewsApi? {
        return retrofit?.create(VNewsApi::class.java)
    }
    private fun createUrl():String {
        var ip = StringBuilder()
        ip.append("http://")
        INDEX.forEach { ip.append(IP[it]) }
        ip.append("/")
        return ip.toString()
    }
}