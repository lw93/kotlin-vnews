package com.xiuyuan.vnews.dto

/**
 * @author Created by xiuyaun
 * @time on 2018/4/21
 */
open class ReqVNewsPage {
    private var vnewsType: String? = null
    private var pageNum: Int? = 1
    private var size: Int = 7
    fun setVnewsType(vnewsType: String?){
        this.vnewsType = vnewsType
    }

    fun getVnewsType(): String?{
        return vnewsType
    }

    fun setPageNum(pageNum: Int?){
        this.pageNum = pageNum
    }

    fun getPageNum(): Int?{
        return pageNum
    }

    fun setSize(size: Int){
        this.size = size
    }

    fun getSize(): Int{
        return size
    }

    override fun toString(): String {
        return "ReqVNewsPage(vnewsType=$vnewsType, pageNum=$pageNum, size=$size)"
    }


}