package com.xiuyuan.vnews.model

import com.xiuyuan.vnews.base.BaseCallBack
import com.xiuyuan.vnews.base.BaseModel
import com.xiuyuan.vnews.bean.VNewsItemBodyVO
import com.xiuyuan.vnews.bean.VNewsItemVO
import com.xiuyuan.vnews.dto.VNewsDTO

/**
 * @author Created by xiuyaun
 * @time on 2018/5/1
 */
interface IVNewsBodyModel: BaseModel {
    fun getVNewsBodyModel(type : String,bodyId : String, call: BaseCallBack<VNewsItemBodyVO>)
}