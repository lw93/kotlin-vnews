package com.xiuyuan.vnews.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 * @author Created by xiuyaun
 * @time on 2018/4/22
 */
internal class IndicatorNavigatorAdapter: CommonNavigatorAdapter {

    private var mTitle: List<String>? = null
    private var mViewPager: ViewPager? = null

    constructor(viewPager: ViewPager?, title: List<String>?){
        this.mViewPager = viewPager
        this.mTitle = title
    }

    override fun getCount(): Int {
        return mTitle?.size ?: 0
    }

    override fun getTitleView(context: Context, i: Int): IPagerTitleView {
        val simplePagerTitleView = SimplePagerTitleView(context)
        simplePagerTitleView.normalColor = Color.LTGRAY
        simplePagerTitleView.selectedColor = Color.WHITE
        simplePagerTitleView.textSize = 12f
        simplePagerTitleView.text = mTitle?.get(i)
        simplePagerTitleView.setOnClickListener { mViewPager?.currentItem = i }
        return simplePagerTitleView
    }


    override fun getIndicator(context: Context): IPagerIndicator {
        val linePagerIndicator = LinePagerIndicator(context)
        linePagerIndicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
        linePagerIndicator.startInterpolator = AccelerateInterpolator()
        linePagerIndicator.endInterpolator = DecelerateInterpolator(1.0f)
        linePagerIndicator.roundRadius = UIUtil.dip2px(context, 3.0).toFloat()
        linePagerIndicator.setColors(Color.WHITE)
        return linePagerIndicator
    }
    /**

    override fun getCount(): Int {
    return if (titles == null) 0 else titles.size
    }

    override fun getTitleView(context: Context, index: Int): IPagerTitleView {
    val simplePagerTitleView = ColorTransitionPagerTitleView(context)
    simplePagerTitleView.setText(titles.get(index))
    simplePagerTitleView.setNormalColor(Color.parseColor("#9e9e9e"))
    simplePagerTitleView.setSelectedColor(Color.parseColor("#00c853"))
    simplePagerTitleView.setOnClickListener(object : View.OnClickListener {
    override fun onClick(v: View) {
    view_pager.setCurrentItem(index)
    }
    })
    return simplePagerTitleView
    }

    override fun getIndicator(context: Context): IPagerIndicator {
    val indicator = LinePagerIndicator(context)
    indicator.mode = LinePagerIndicator.MODE_EXACTLY
    indicator.lineHeight = UIUtil.dip2px(context, 6.0).toFloat()
    indicator.lineWidth = UIUtil.dip2px(context, 10.0).toFloat()
    indicator.roundRadius = UIUtil.dip2px(context, 3.0).toFloat()
    indicator.startInterpolator = AccelerateInterpolator()
    indicator.endInterpolator = DecelerateInterpolator(2.0f)
    indicator.setColors(Color.parseColor("#00c853"))
    return indicator
     */
}