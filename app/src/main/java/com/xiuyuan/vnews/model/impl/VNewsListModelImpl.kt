package com.xiuyuan.vnews.model.impl

import android.util.Log
import com.blankj.utilcode.util.NetworkUtils
import com.xiuyuan.vnews.base.BaseCallBack
import com.xiuyuan.vnews.base.VNewsApplication
import com.xiuyuan.vnews.bean.MessageEvent
import com.xiuyuan.vnews.bean.VNewsItemVO
import com.xiuyuan.vnews.bean.VNewsPageVO
import com.xiuyuan.vnews.db.GreenDaoHelper
import com.xiuyuan.vnews.db.VNewsEntity
import com.xiuyuan.vnews.dto.ReqVNewsPage
import com.xiuyuan.vnews.dto.VNewsDTO
import com.xiuyuan.vnews.http.ExceptionHandle
import com.xiuyuan.vnews.http.VNewsHttp
import com.xiuyuan.vnews.model.IVNewsListModel
import com.xiuyuan.vnews.utils.VNewsUtil
import greendao.VNewsEntityDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DefaultSubscriber
import org.greenrobot.eventbus.EventBus

/**
 * @author Created by xiuyaun
 * @time on 2018/4/24
 */
internal class VNewsListModelImpl : IVNewsListModel{
    private val TAG = VNewsListModelImpl::class.java.simpleName

    constructor()

    override fun getVNewsListModel(type : String?, evenBus: EventBus?,call: BaseCallBack<List<VNewsItemVO>>) {
        var param = ReqVNewsPage()
        param.setVnewsType(type)
        var app = VNewsApplication.vNewsHttp
        var http =app?.createApi()
        Log.d(TAG, "vNewsHttp init "+ (http !=null).toString())
        Log.d(TAG, "ReqVNewsPage "+ param.toString())
        http?.getVNews(param)
                ?.subscribeOn(Schedulers.from(VNewsHttp.pool))
                ?.doOnNext(object :Consumer <VNewsDTO<VNewsPageVO<VNewsItemVO>>>{
                    override fun accept(t: VNewsDTO<VNewsPageVO<VNewsItemVO>>) {
                        Log.d(TAG, "Consumer init " + Thread.currentThread().name)
                        var isWifiAvailable = NetworkUtils.isWifiAvailable()
                        Log.d(TAG, "isWifiAvailable " + isWifiAvailable)
                        var isWifiConnected = NetworkUtils.isWifiConnected()
                        Log.d(TAG, "isWifiConnected " + isWifiConnected)
                        if (isWifiAvailable && isWifiConnected) {
                            var data = t.getData()?.getList()
                            var size = data?.size
                            Log.d(TAG, "Consumer data size " + size)
                            size?.takeIf { it > 0 }?.apply {
                                var userDao = GreenDaoHelper.INSTANCE.getDaoSession()?.vNewsEntityDao
                                data?.forEach {
                                    var entity = VNewsEntity()
                                    entity.vnewsDocId = it.getDocId()
                                    entity.vnewsEditor = it.getEditor()
                                    entity.vnewsImgExtra = it.getImgExtra()
                                    entity.vnewsImgUrl = it.getImgUrl()
                                    entity.vnewsRelativeKey = it.getRelativeKey()
                                    entity.vnewsResouce = it.getResouce()
                                    entity.vnewsShortContent = it.getShortContent()
                                    entity.vnewsTitle = it.getTitle()
                                    entity.vnewsType = it.getType()
                                    entity.vnewsBody = ""
                                    try {
                                        userDao?.insert(entity)
                                        Log.d(TAG, "data insert " + entity.toString())
                                    } catch (exception: Exception) {
                                        Log.d(TAG, "data insert failed " + exception)
                                    }
                                }
                            }
                        }
                    }
                })
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DefaultSubscriber<VNewsDTO<VNewsPageVO<VNewsItemVO>>>(){

                    override fun onComplete() {
                        call.onFinshed()
                    }

                    override fun onError(t: Throwable?) {
                        Log.d(TAG, "onError " + t.toString())
                        var message = t?.let { ExceptionHandle.handleException(it) }
                        Log.d(TAG, "onError message " + VNewsUtil.UNCONNECT.equals(message))
                        if (VNewsUtil.UNCONNECT.equals(message)) {
                            VNewsHttp.pool.execute(object : Runnable {
                                override fun run() {
                                    var list = ArrayList<VNewsItemVO>(VNewsUtil.QUERY_SIZE)
                                    Log.d(TAG, "onError 网络 " + t.toString())
                                    var query = GreenDaoHelper.INSTANCE.getDaoSession()?.vNewsEntityDao?.queryBuilder()
                                    var data = query?.where(VNewsEntityDao.Properties.VnewsType.eq(type))
                                            ?.orderAsc(VNewsEntityDao.Properties.VnewsModified)?.limit(VNewsUtil.QUERY_SIZE)?.list()
                                    Log.d(TAG, "onError size " + data?.size)
                                    data?.forEach {
                                        var item = VNewsItemVO()
                                        item.setDocId(it.vnewsDocId)
                                        item.setEditor(it.vnewsEditor)
                                        item.setImgExtra(it.vnewsImgExtra)
                                        item.setRelativeKey(it.vnewsRelativeKey)
                                        item.setResouce(it.vnewsResouce)
                                        item.setShortContent(it.vnewsShortContent)
                                        item.setTitle(it.vnewsTitle)
                                        item.setType(it.vnewsType)
                                        item.setimgUrl(it.vnewsImgUrl)
                                        list.add(item)

                                    }
                                    evenBus?.post(MessageEvent(message!!,0,list))
                                }
                            })
                        }else{
                            call.onFailed(message)
                        }
                    }

                    override fun onNext(t: VNewsDTO<VNewsPageVO<VNewsItemVO>>?) {
                        Log.d(TAG,"onNext "+ t.toString())
                        call.onSuccess(t?.getData()?.getList())
                     }
                })
    }

    override fun getVNewsListMoreModel(type: String?, pageNum: Int?,evenBus: EventBus?, call: BaseCallBack<List<VNewsItemVO>>) {
        var param = ReqVNewsPage()
        param.setVnewsType(type)
        param.setPageNum(pageNum)
        var app = VNewsApplication.vNewsHttp
        var http =app?.createApi()
        Log.d(TAG, "vNewsHttp init "+ (http !=null).toString())
        http?.getVNews(param)
                ?.subscribeOn(Schedulers.from(VNewsHttp.pool))
                ?.doOnNext(object :Consumer <VNewsDTO<VNewsPageVO<VNewsItemVO>>>{
                    override fun accept(t: VNewsDTO<VNewsPageVO<VNewsItemVO>>) {
                        Log.d(TAG, "Consumer init " + Thread.currentThread().name)
                        var isWifiAvailable = NetworkUtils.isWifiAvailable()
                        Log.d(TAG, "isWifiAvailable " + isWifiAvailable)
                        var isWifiConnected = NetworkUtils.isWifiConnected()
                        Log.d(TAG, "isWifiConnected " + isWifiConnected)
                        if (isWifiAvailable && isWifiConnected) {
                            var data = t.getData()?.getList()
                            var size = data?.size
                            Log.d(TAG, "Consumer data size " + size)
                            size?.takeIf { it > 0 }?.apply {
                                var userDao = GreenDaoHelper.INSTANCE.getDaoSession()?.vNewsEntityDao
                                data?.forEach {
                                    var entity = VNewsEntity()
                                    entity.vnewsDocId = it.getDocId()
                                    entity.vnewsEditor = it.getEditor()
                                    entity.vnewsImgExtra = it.getImgExtra()
                                    entity.vnewsImgUrl = it.getImgUrl()
                                    entity.vnewsRelativeKey = it.getRelativeKey()
                                    entity.vnewsResouce = it.getResouce()
                                    entity.vnewsShortContent = it.getShortContent()
                                    entity.vnewsTitle = it.getTitle()
                                    entity.vnewsType = it.getType()
                                    entity.vnewsBody = ""
                                    try {
                                        userDao?.insert(entity)
                                    } catch (exception: Exception) {
                                        Log.d(TAG, "data insert failed " + exception)
                                    }
                                }
                            }
                        }
                    }
                })
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DefaultSubscriber<VNewsDTO<VNewsPageVO<VNewsItemVO>>>(){

                    override fun onComplete() {
                        call.onFinshed()
                    }

                    override fun onError(t: Throwable?) {
                        Log.d(TAG,"onError "+ t.toString())
                        var message = t?.let { ExceptionHandle.handleException(it) }
                        Log.d(TAG,"onError message "+ VNewsUtil.UNCONNECT.equals(message))
                        if (VNewsUtil.UNCONNECT.contentEquals(message.toString())){
                            VNewsHttp.pool.execute(object : Runnable {
                                override fun run() {
                                    Log.d(TAG,"onError 网络 "+ t.toString())
                                    var list = ArrayList<VNewsItemVO>(VNewsUtil.QUERY_SIZE)
                                    var query = GreenDaoHelper.INSTANCE.getDaoSession()?.vNewsEntityDao?.queryBuilder()
                                    var data = query?.where(VNewsEntityDao.Properties.VnewsType.eq(type))
                                            ?.orderAsc(VNewsEntityDao.Properties.VnewsModified)
                                            ?.offset(((pageNum?.minus(1))?.times( VNewsUtil.QUERY_SIZE)!!))
                                            ?.limit(VNewsUtil.QUERY_SIZE)?.list()
                                    Log.d(TAG,"onError size "+ data?.size)
                                    data?.forEach {
                                        var item = VNewsItemVO()
                                        item.setDocId(it.vnewsDocId)
                                        item.setEditor(it.vnewsEditor)
                                        item.setImgExtra(it.vnewsImgExtra)
                                        item.setRelativeKey(it.vnewsRelativeKey)
                                        item.setResouce(it.vnewsResouce)
                                        item.setShortContent(it.vnewsShortContent)
                                        item.setTitle(it.vnewsTitle)
                                        item.setType(it.vnewsType)
                                        item.setimgUrl(it.vnewsImgUrl)
                                        list.add(item)
                                    }
                                    evenBus?.post(MessageEvent(message!!,1,list))
                                }
                            })
                        }else{
                            call.onFailed(message)
                        }
                    }

                    override fun onNext(t: VNewsDTO<VNewsPageVO<VNewsItemVO>>?) {
                        Log.d(TAG,"onNext "+ t.toString())
                        call.onSuccess(t?.getData()?.getList())
                    }
                })
    }
}
