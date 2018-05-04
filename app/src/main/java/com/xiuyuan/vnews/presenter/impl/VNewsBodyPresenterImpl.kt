package com.xiuyuan.vnews.presenter.impl

import com.xiuyuan.vnews.base.BaseCallBack
import com.xiuyuan.vnews.base.BasePresenter
import com.xiuyuan.vnews.base.BaseView
import com.xiuyuan.vnews.bean.VNewsItemBodyVO
import com.xiuyuan.vnews.model.impl.VNewsBodyModelImpl
import com.xiuyuan.vnews.presenter.IVNewsBodyPresenter
import com.xiuyuan.vnews.view.IVNewsBodyView

/**
 * @author Created by xiuyaun
 * @time on 2018/5/1
 */
internal class VNewsBodyPresenterImpl constructor(view: BaseView?) : BasePresenter(), IVNewsBodyPresenter {
    private var view:IVNewsBodyView? = view as IVNewsBodyView
    private var model : VNewsBodyModelImpl? = null

    override fun getVNewsItemBodyPresenter(type: String, bodyId: String) {
        if (null != view){
            if (null == model){
                model = VNewsBodyModelImpl()
            }
            model?.getVNewsBodyModel(type,bodyId,object : BaseCallBack<VNewsItemBodyVO> {
                override fun onSuccess(callback: VNewsItemBodyVO?) {
                    view?.onSuccess(callback)
                }

                override fun onFailed(message: String?) {
                    view?.onFailed(message)
                }

                override fun onFinshed() {
                    view?.releaseView()
                }
            })
        }
    }

    override fun detachView() {
        if (null != view){
            view = null
        }else if (null != model){
            model = null
        }
    }

}