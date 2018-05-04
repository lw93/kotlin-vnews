package com.xiuyuan.vnews.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xiuyuan.vnews.utils.ToastUtil
import org.greenrobot.eventbus.EventBus


/**
 * @author Created by xiuyaun
 * @time on 2018/4/18
 */

abstract class BaseActivity<P : BasePresenter>: AppCompatActivity(){
    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView())
        presenter = initPresenter()
        initView()
    }
    protected abstract fun bindView():Int
    protected abstract fun initPresenter(): P?
    protected abstract fun initView()
    override fun onDestroy() {
        super.onDestroy()
        if (null != presenter){
            presenter?.detachView()
            presenter = null
        }
        ToastUtil.cancelToast()
    }

}
