package com.xiuyuan.vnews.model.impl

import android.content.ContentValues
import android.util.Log
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.StringUtils
import com.xiuyuan.vnews.base.BaseCallBack
import com.xiuyuan.vnews.base.VNewsApplication
import com.xiuyuan.vnews.bean.VNewsItemBodyVO
import com.xiuyuan.vnews.db.GreenDaoHelper
import com.xiuyuan.vnews.dto.ReqVNewsItem
import com.xiuyuan.vnews.dto.VNewsDTO
import com.xiuyuan.vnews.http.ExceptionHandle
import com.xiuyuan.vnews.http.VNewsHttp
import com.xiuyuan.vnews.model.IVNewsBodyModel
import com.xiuyuan.vnews.utils.VNewsUtil
import greendao.VNewsEntityDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DefaultSubscriber

/**
 * @author Created by xiuyaun
 * @time on 2018/5/1
 */
internal class VNewsBodyModelImpl : IVNewsBodyModel{
    private val TAG = VNewsBodyModelImpl::class.java.simpleName
    constructor()

    override fun getVNewsBodyModel(type: String, bodyId: String, call: BaseCallBack<VNewsItemBodyVO>) {
        var param = ReqVNewsItem()
        param.setVnewsType(type)
        param.setBodyId(bodyId)
        var app = VNewsApplication.vNewsHttp
        var http =app?.createApi()
        Log.d(TAG, "vNewsHttp init "+ (http !=null).toString())
        Log.d(TAG, "ReqVNewsItem "+ param.toString())
        http?.getVNewsBody(param)
                ?.subscribeOn(Schedulers.from(VNewsHttp.pool))
                ?.doOnNext(object : Consumer<VNewsDTO<VNewsItemBodyVO>> {
                    override fun accept(t: VNewsDTO<VNewsItemBodyVO>) {
                        Log.d(TAG, "Consumer init " + Thread.currentThread().name)
                        var isWifiAvailable = NetworkUtils.isWifiAvailable()
                        Log.d(TAG, "isWifiAvailable " + isWifiAvailable)
                        var isWifiConnected = NetworkUtils.isWifiConnected()
                        Log.d(TAG, "isWifiConnected " + isWifiConnected)
                        if (isWifiAvailable && isWifiConnected) {
                            var data = t.getData()
                            var body = data?.getBody()
                            Log.d(TAG, "Consumer data length " + body?.length)
                            try {
                                body?.takeIf { !StringUtils.isEmpty(it) }?.apply {
                                    var userDao = GreenDaoHelper.INSTANCE.getDB()
                                    var value = ContentValues()
                                    value.put(VNewsEntityDao.Properties.VnewsBody.columnName,body)
                                    userDao?.update(VNewsUtil.TABLE,value,"${VNewsEntityDao.Properties.VnewsDocId.columnName}=?",arrayOf(bodyId))
                                }
                            } catch (exception: Exception) {
                                Log.d(TAG, "data insert failed " + exception)
                            }
                        }
                    }
                })
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DefaultSubscriber<VNewsDTO<VNewsItemBodyVO>>(){

                    override fun onComplete() {
                        call.onFinshed()
                    }

                    override fun onError(t: Throwable) {
                        Log.d(TAG,"onError "+ t.toString())
                        var message = t?.let { ExceptionHandle.handleException(it) }
                        if (VNewsUtil.UNCONNECT.equals(message)){
                            var query = GreenDaoHelper.INSTANCE.getDaoSession()?.vNewsEntityDao?.queryBuilder()
                            var entity = query?.where(VNewsEntityDao.Properties.VnewsDocId.eq(bodyId),VNewsEntityDao.Properties.VnewsType.eq(type))?.unique()
                            var data = VNewsItemBodyVO()
                            entity?.vnewsBody?.let { data.setBody(it) }
                            call.onSuccess(data)
                            call.onFinshed()
                        }
                        call.onFailed(message)
                    }

                    override fun onNext(t: VNewsDTO<VNewsItemBodyVO>) {
                        Log.d(TAG,"onNext "+ t.toString())
                        call.onSuccess(t.getData())
                    }
                })
    }
}