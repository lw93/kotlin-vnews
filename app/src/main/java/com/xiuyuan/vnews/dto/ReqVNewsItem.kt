package com.xiuyuan.vnews.dto

/**
 * @author Created by xiuyaun
 * @time on 2018/4/21
 */
open class ReqVNewsItem {
    private var vnewsType: String? = null
    private var bodyId: String? = null

    fun setVnewsType(vnewsType: String?){
        this.vnewsType = vnewsType
    }

    fun getVnewsType(): String?{
        return vnewsType
    }

    fun setBodyId(bodyId: String?){
        this.bodyId = bodyId
    }

    fun getBodyId(): String?{
        return bodyId
    }
    override fun toString(): String {
        return "ReqVNewsItem(vnewsType=$vnewsType, bodyId=$bodyId)"
    }


}