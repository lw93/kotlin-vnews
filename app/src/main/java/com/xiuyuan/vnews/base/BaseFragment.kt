package com.xiuyuan.vnews.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xiuyuan.vnews.utils.ToastUtil

/**
 * @author Created by xiuyaun
 * @time on 2018/4/22
 */
abstract class BaseFragment<P: BasePresenter>: Fragment() {
    protected var presenter: P? = null
    protected var mContext: Context? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context != null) {
            mContext = context
        } else {
            mContext = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(bindLayout(), container, false);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
        initView()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (null != presenter) {
            presenter?.detachView()
            presenter = null
        }else if (null != mContext){
            mContext = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ToastUtil.cancelToast()
    }

    override fun onDetach() {
        super.onDetach()
    }

    protected abstract fun initPresenter(): P?
    protected abstract fun initView()
    protected abstract fun bindLayout():Int
}