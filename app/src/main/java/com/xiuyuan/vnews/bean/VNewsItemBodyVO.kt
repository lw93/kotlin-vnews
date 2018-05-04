package com.xiuyuan.vnews.bean

/**
 * @author Created by xiuyaun
 * @time on 2018/4/18
 */

open class VNewsItemBodyVO {
    private var body: String? = null

    fun setBody(body : String) {
       this.body = body
    }
    fun getBody(): String? {
       return body
    }

    override fun toString(): String {
        return "VNewsItemBodyVO(body=$body)"
    }
}
