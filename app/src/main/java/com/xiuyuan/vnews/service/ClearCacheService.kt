package com.xiuyuan.vnews.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.xiuyuan.vnews.db.GreenDaoHelper
import com.xiuyuan.vnews.http.VNewsHttp

/**
 * @author Created by xiuyaun
 * @time on 2018/5/5
 */
internal class ClearCacheService : IntentService("ClearCacheService") {
    val TAG = ClearCacheService::class.java.simpleName
    override fun onHandleIntent(intent: Intent?) {
        VNewsHttp.pool.execute(object : Runnable {
            override fun run() {
                Log.d(TAG,"ClearCacheService start")
                var dao = GreenDaoHelper.INSTANCE.getDaoSession()?.vNewsEntityDao
                var list = dao?.loadAll()
                var size = list?.size
                if (size!! > 0) {
                    dao?.deleteAll()
                }
            }
        })
    }
}