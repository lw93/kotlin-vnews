package com.xiuyuan.vnews

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.xiuyuan.vnews.base.BaseActivity
import com.xiuyuan.vnews.base.BasePresenter
import com.xiuyuan.vnews.activity.MainActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity


internal class SplashActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        Handler().postDelayed(object : Runnable {
            override fun run() {
                finish()
            }
        },2000)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
