package com.xiuyuan.vnews.presenter

import com.xiuyuan.vnews.base.BasePresenter
import com.xiuyuan.vnews.bean.VNewsItemVO
import retrofit2.Callback

/**
 * @author Created by xiuyaun
 * @time on 2018/4/24
 */
internal interface IVNewsListPresenter  {
    fun getVNewsListPresenter(type: String?)
    fun getVNewsListMorePresenter(type: String?,pageNum : Int?)

}