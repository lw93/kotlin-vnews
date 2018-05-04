package com.xiuyuan.vnews.api

import com.xiuyuan.vnews.bean.VNewsItemBodyVO
import com.xiuyuan.vnews.bean.VNewsItemVO
import com.xiuyuan.vnews.bean.VNewsPageVO
import com.xiuyuan.vnews.dto.ReqVNewsItem
import com.xiuyuan.vnews.dto.ReqVNewsPage
import com.xiuyuan.vnews.dto.VNewsDTO
import retrofit2.http.POST
import io.reactivex.Flowable
import retrofit2.http.Body

/**
 * @author Created by xiuyaun
 * @time on 2018/4/19
 */
interface VNewsApi {
    @POST("vnews/queryAll")
    fun getVNews(@Body reqVNewsPage: ReqVNewsPage):Flowable<VNewsDTO<VNewsPageVO<VNewsItemVO>>>

    @POST("vnews/queryBody")
    fun getVNewsBody(@Body reqVNewsItem: ReqVNewsItem):Flowable<VNewsDTO<VNewsItemBodyVO>>
}