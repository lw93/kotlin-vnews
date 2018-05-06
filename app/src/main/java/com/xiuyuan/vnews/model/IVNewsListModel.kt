package com.xiuyuan.vnews.model

import com.xiuyuan.vnews.base.BaseCallBack
import com.xiuyuan.vnews.base.BaseModel
import com.xiuyuan.vnews.bean.VNewsItemVO
import org.greenrobot.eventbus.EventBus

/**
 * @author Created by xiuyaun
 * @time on 2018/4/24
 */
internal interface IVNewsListModel : BaseModel {
    fun getVNewsListModel(type : String?, evenBus: EventBus?, call:BaseCallBack<List<VNewsItemVO>>)
    fun getVNewsListMoreModel(type : String?, pageNum : Int?,evenBus: EventBus?, call : BaseCallBack<List<VNewsItemVO>>)
}