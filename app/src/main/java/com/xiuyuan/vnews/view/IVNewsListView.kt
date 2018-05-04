package com.xiuyuan.vnews.view

import com.xiuyuan.vnews.base.BaseView
import com.xiuyuan.vnews.bean.VNewsItemVO

/**
 * @author Created by xiuyaun
 * @time on 2018/4/23
 */
interface IVNewsListView : BaseView {
    fun releaseView()
    fun onSuccess(data : List<VNewsItemVO>?)
    fun updateRecyclerView(data : List<VNewsItemVO>?)
}