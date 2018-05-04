package com.xiuyuan.vnews.view

import com.xiuyuan.vnews.base.BaseView
import com.xiuyuan.vnews.bean.VNewsItemBodyVO
import com.xiuyuan.vnews.bean.VNewsItemVO
import com.xiuyuan.vnews.dto.VNewsDTO

/**
 * @author Created by xiuyaun
 * @time on 2018/5/1
 */
interface IVNewsBodyView : BaseView {
    fun onSuccess(data : VNewsItemBodyVO?)
    fun releaseView()
}