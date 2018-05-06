package com.xiuyuan.vnews.presenter

import org.greenrobot.eventbus.EventBus

/**
 * @author Created by xiuyaun
 * @time on 2018/4/24
 */
internal interface IVNewsListPresenter  {
    fun getVNewsListPresenter(type: String?,evenBus: EventBus?)
    fun getVNewsListMorePresenter(type: String?,pageNum : Int?,evenBus: EventBus?)

}