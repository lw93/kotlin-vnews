package com.xiuyuan.vnews.presenter

import com.xiuyuan.vnews.base.BasePresenter

/**
 * @author Created by xiuyaun
 * @time on 2018/5/1
 */
internal interface IVNewsBodyPresenter {
    fun getVNewsItemBodyPresenter(type: String,bodyId:String)
}