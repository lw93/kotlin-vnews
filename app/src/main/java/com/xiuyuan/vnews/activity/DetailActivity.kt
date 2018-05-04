package com.xiuyuan.vnews.activity

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.xiuyuan.vnews.R
import com.xiuyuan.vnews.base.BaseActivity
import com.xiuyuan.vnews.bean.VNewsItemBodyVO
import com.xiuyuan.vnews.fragment.VNewsListFragment
import com.xiuyuan.vnews.presenter.impl.VNewsBodyPresenterImpl
import com.xiuyuan.vnews.utils.ToastUtil
import com.xiuyuan.vnews.view.IVNewsBodyView
import kotlinx.android.synthetic.main.activity_detail.*


internal class DetailActivity : BaseActivity<VNewsBodyPresenterImpl>() ,IVNewsBodyView{
    private val TAG = DetailActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun bindView(): Int {
        return R.layout.activity_detail
    }

    override fun initPresenter(): VNewsBodyPresenterImpl? {
        return VNewsBodyPresenterImpl(this)
    }

    override fun initView() {
        Log.d(TAG,"initView")
        var type = intent.getStringExtra(VNewsListFragment.FRAGMENT_TYPE_PARME)
        var bodyId = intent.getStringExtra(VNewsListFragment.FRAGMENT_ITEM_BODYID)
        var title = intent.getStringExtra(VNewsListFragment.FRAGMENT_ITEM_TITLE)
        if (!title.isNullOrEmpty()) supportActionBar?.setTitle(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG,"Message1:"+ type)
        Log.d(TAG,"Message2:"+ bodyId)
        Log.d(TAG, "present init "+ (presenter !=null).toString())
        detail_progressbar.visibility = View.VISIBLE
        detail_fl.postDelayed(object : Runnable{
            override fun run() {
                presenter?.getVNewsItemBodyPresenter(type,bodyId)
            }
        },1000)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.share_action -> {
                Log.d(TAG, "share_action")
                ToastUtil.showShort("该功能暂未开启")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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
    override fun onDestroy() {
        super.onDestroy()
        clearAll()
    }
    private fun clearAll(){

    }

    override fun onFailed(message: String?) {
        detail_progressbar?.takeIf { it.isShown  }?.apply {
            detail_progressbar.visibility = View.GONE
        }
        if (null != message) {
            ToastUtil.showShort(message)
        }
    }

    override fun onSuccess(data: VNewsItemBodyVO?) {
        data?.getBody()?.takeIf {
            it.contains("版本太低") || it.contains("更新版本")
                    || it.contains("使用安卓和IPhone最新版本") || it.isNullOrEmpty()
        } ?.apply {
            var child = View.inflate(this@DetailActivity,R.layout.empty_list,null)
            child.findViewById<TextView>(R.id.empty).text = getString(R.string.no_more_data)
            detail_fl.addView(child)
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txt_detail.text = Html.fromHtml(data?.getBody(),Html.FROM_HTML_MODE_LEGACY)
            return
        }
        txt_detail.text = Html.fromHtml(data?.getBody())
    }

    override fun releaseView() {
        Log.d(TAG,"releaseView")
        detail_progressbar?.takeIf { it.isShown  }?.apply {
            detail_progressbar.visibility = View.GONE
        }
    }
}