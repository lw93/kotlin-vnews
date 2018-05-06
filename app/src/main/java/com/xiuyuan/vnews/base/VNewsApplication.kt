package com.xiuyuan.vnews.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Handler
import android.util.Log
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.Utils
import com.xiuyuan.vnews.http.VNewsHttp
import android.support.multidex.MultiDex
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.squareup.leakcanary.LeakCanary
import com.xiuyuan.vnews.BuildConfig
import com.xiuyuan.vnews.db.GreenDaoHelper
import com.xiuyuan.vnews.service.ClearCacheService
import com.xiuyuan.vnews.utils.ScheduleUtil
import com.xiuyuan.vnews.utils.VNewsUtil
import org.greenrobot.eventbus.EventBus


/**
 * @author Created by xiuyaun
 * @time on 2018/4/20
 */
open class VNewsApplication : Application(),CrashUtils.OnCrashListener {

    companion object {
        val TAG = VNewsApplication::class.java.simpleName
        var vNewsContext: Context? = null
        var vNewsHttp: VNewsHttp? = null
    }
    override fun onCreate() {
        super.onCreate()
        initVNews()

    }
    @SuppressLint("MissingPermission")
    private fun initVNews(){
        vNewsContext = applicationContext
        Utils.init(this)
        CrashUtils.init(this)
        EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .throwSubscriberException(BuildConfig.DEBUG)
                .installDefaultEventBus()
        vNewsHttp = VNewsHttp.INSTANCE
        GreenDaoHelper.initDatabase()
        ScheduleUtil.startScheduleService(this, ClearCacheService::class.java,ScheduleUtil.ACTION)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    override fun onCrash(crashInfo: String?, e: Throwable?) {
        ToastUtils.showShort(VNewsUtil.CRASH_MESSAGE)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                AppUtils.relaunchApp();
            }
        },1500)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun onLowMemory() {
        // 低内存的时候执行
        super.onLowMemory()
        Log.d(TAG, "onLowMemory")
    }

    override fun onTrimMemory(level: Int) {
        // 程序在内存清理的时候执行
        super.onTrimMemory(level)
        Log.d(TAG, "onTrimMemory")
    }

    override fun onTerminate() {
        // 程序终止的时候执行
        super.onTerminate()
        Log.d(TAG, "onTerminate")
        clearAll()
    }

    private fun clearAll(){
        if (null != vNewsHttp) {
            vNewsHttp = null
        }else if (null != vNewsContext){
            vNewsContext = null
        }
        ScheduleUtil.stopScheduleService(this,ClearCacheService::class.java,ScheduleUtil.ACTION)
    }
}