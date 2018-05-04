package com.xiuyuan.vnews.presenter.impl

import com.xiuyuan.vnews.base.BaseCallBack
import com.xiuyuan.vnews.base.BasePresenter
import com.xiuyuan.vnews.base.BaseView
import com.xiuyuan.vnews.bean.VNewsItemVO
import com.xiuyuan.vnews.model.impl.VNewsListModelImpl
import com.xiuyuan.vnews.presenter.IVNewsListPresenter
import com.xiuyuan.vnews.view.IVNewsListView

/**
 * @author Created by xiuyaun
 * @time on 2018/4/24
 */
internal class VNewsListPresenterImpl constructor(view: BaseView?) : BasePresenter(), IVNewsListPresenter {

    private var view:IVNewsListView? = view as IVNewsListView
    private var model : VNewsListModelImpl? = null



    override fun getVNewsListPresenter(type : String?) {
        if (null != view){
            if (null == model){
                model = VNewsListModelImpl()
            }
            model ?.getVNewsListModel(type,object : BaseCallBack<List<VNewsItemVO>> {
                override fun onSuccess(callback: List<VNewsItemVO>?) {
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
    override fun getVNewsListMorePresenter(type: String?, pageNum: Int?) {
        if (null != view){
            if (null == model){
                model = VNewsListModelImpl()
            }
            model ?.getVNewsListMoreModel(type,pageNum,object : BaseCallBack<List<VNewsItemVO>> {
                override fun onSuccess(callback: List<VNewsItemVO>?) {
                    view?.updateRecyclerView(callback)
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