package com.xiuyuan.vnews

import android.util.Log
import com.xiuyuan.vnews.bean.MessageEvent
import com.xiuyuan.vnews.bean.VNewsItemVO
import com.xiuyuan.vnews.bean.VNewsPageVO
import com.xiuyuan.vnews.db.VNews
import com.xiuyuan.vnews.dto.ReqVNewsPage
import com.xiuyuan.vnews.dto.VNewsDTO
import com.xiuyuan.vnews.http.VNewsHttp
import com.xiuyuan.vnews.presenter.impl.VNewsListPresenterImpl
import com.xiuyuan.vnews.utils.GsonUtil
import com.xiuyuan.vnews.view.IVNewsListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DefaultSubscriber
import org.junit.Test

/**
 * @author Created by xiuyaun
 * @time on 2018/4/20
 */
class Test {

    @Test
    fun main(){
      System.out.print("main")
    }
}