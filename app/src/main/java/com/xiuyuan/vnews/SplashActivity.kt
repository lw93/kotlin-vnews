package com.xiuyuan.vnews

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.blankj.utilcode.util.ActivityUtils
import com.xiuyuan.vnews.activity.MainActivity


internal class SplashActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                ActivityUtils.startActivity( MainActivity::class.java)
                finish()
            }
        },3000)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
