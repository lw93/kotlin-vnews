package com.xiuyuan.vnews.adapter

import android.os.Build
import android.view.View
import android.widget.ImageView
import com.blankj.utilcode.util.StringUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xiuyuan.vnews.R
import com.xiuyuan.vnews.bean.VNewsItemVO

/**
 * @author Created by xiuyaun
 * @time on 2018/4/22
 */
internal class VNewsListAdapter: BaseMultiItemQuickAdapter<VNewsItemVO, BaseViewHolder> {

    constructor(data:List<VNewsItemVO>?) : super(data) {
        addItemType(0, R.layout.item_list_fragment)
        //addItemType(NewsListItemBean.NORMAL, R.layout.item_news_list_normal_layout)
    }

    override fun convert(helper: BaseViewHolder, item: VNewsItemVO) {
        when (helper.itemViewType) {
            0 -> {
                var imageView:ImageView = helper.getView(R.id.list_item_image)
                Glide.with(mContext)
                        .load(item.getImgUrl())
                        .transition(withCrossFade())
                        .apply(RequestOptions.centerCropTransform())
                        .apply(RequestOptions.errorOf(R.drawable.empty))
                        .apply(RequestOptions.skipMemoryCacheOf(false))
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                        .into(imageView)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    helper.getView<View>(R.id.list_item_rl).setBackground(null)
                }else {
                    helper.getView<View>(R.id.list_item_rl).setBackgroundDrawable(null)
                }
                helper.setText(R.id.list_item_title,item.getTitle())
                helper.setText(R.id.list_item_content,StringUtils.toDBC(item.getShortContent()))
                helper.setText(R.id.list_item_editor,item.getEditor())
                helper.setText(R.id.list_item_resouce,item.getResouce())
            }

        }
    }
}

