package com.xiuyuan.vnews.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup



/**
 * @author Created by xiuyaun
 * @time on 2018/4/22
 */
internal class FragmentAdapter: FragmentStatePagerAdapter {

    private var mFragmentArray: List<Fragment>? = null

    constructor(fm:FragmentManager?, fragmentArray: List<Fragment>?):super(fm){
        this.mFragmentArray = fragmentArray
    }

    override fun getCount(): Int {
        return mFragmentArray?.size ?: 0
    }

    override fun getItem(position: Int): Fragment? {
        return mFragmentArray?.get(position)
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }


}