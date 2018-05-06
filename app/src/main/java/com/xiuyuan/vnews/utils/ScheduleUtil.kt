package com.xiuyuan.vnews.utils

import android.app.PendingIntent
import android.support.v4.view.accessibility.AccessibilityEventCompat.setAction
import android.content.Intent
import android.content.Context.ALARM_SERVICE
import android.app.AlarmManager
import android.content.Context
import android.os.Build
import android.os.SystemClock



/**
 * @author Created by xiuyaun
 * @time on 2018/5/5
 */
object ScheduleUtil {
    val ACTION = "CLEAR_CACHE"
    //开启轮询服务
    fun startScheduleService(context: Context, cls: Class<*>, action: String) {
        //获取AlarmManager系统服务
        val manager = context .getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //包装需要执行Service的Intent
        val intent = Intent(context, cls)
        intent.action = action
        val pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //触发服务的起始时间
        val triggerAtTime = SystemClock.elapsedRealtime()

        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
                    3600 * 24  * 1000, pendingIntent)
        }


    //停止轮询服务
    fun stopScheduleService(context: Context, cls: Class<*>, action: String) {
        val manager = context .getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, cls)
        intent.action = action
        val pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
        //取消正在执行的服务
        manager.cancel(pendingIntent)
    }
}