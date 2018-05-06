package com.xiuyuan.vnews.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.xiuyuan.vnews.R
import com.xiuyuan.vnews.adapter.FragmentAdapter
import com.xiuyuan.vnews.adapter.IndicatorNavigatorAdapter
import com.xiuyuan.vnews.base.BaseActivity
import com.xiuyuan.vnews.base.BasePresenter
import com.xiuyuan.vnews.bean.MessageEvent
import com.xiuyuan.vnews.fragment.VNewsListFragment
import kotlinx.android.synthetic.main.activity_main.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : BaseActivity<BasePresenter>() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun bindView(): Int {
        return  R.layout.activity_main
     }

    override fun initPresenter(): BasePresenter? {
        return null
    }

    override fun initView() {
        var titles = resources.getStringArray(R.array.vnews_type)
        var mFragmentList = ArrayList<Fragment>(titles.size)
        titles.forEach { mFragmentList.add(VNewsListFragment.newInstance(it)) }
        view_pager.adapter = FragmentAdapter(supportFragmentManager, mFragmentList)
        Log.d(TAG, mFragmentList.size.toString())
        var mCommonNavigator = CommonNavigator(this)
        mCommonNavigator.isSmoothScroll = true
        mCommonNavigator.isFollowTouch = true
        mCommonNavigator.scrollPivotX = 0.65f
        mCommonNavigator.adapter  = IndicatorNavigatorAdapter(view_pager, titles.toList())
        magic_indicator.navigator = mCommonNavigator
        ViewPagerHelper.bind(magic_indicator, view_pager)
    }



    override fun onDestroy() {
        super.onDestroy()
    }
}