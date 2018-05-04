package com.xiuyuan.vnews.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.xiuyuan.vnews.R
import com.xiuyuan.vnews.activity.DetailActivity
import com.xiuyuan.vnews.adapter.VNewsListAdapter
import com.xiuyuan.vnews.base.BaseFragment
import com.xiuyuan.vnews.bean.VNewsItemVO
import com.xiuyuan.vnews.presenter.impl.VNewsListPresenterImpl
import com.xiuyuan.vnews.utils.ToastUtil
import com.xiuyuan.vnews.utils.VNewsUtil
import com.xiuyuan.vnews.view.IVNewsListView
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * @author Created by xiuyaun
 * @time on 2018/4/22
 */
internal class VNewsListFragment:BaseFragment<VNewsListPresenterImpl>(),IVNewsListView, BaseQuickAdapter.OnItemClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private var pageNum = 1
    private lateinit var list:List<VNewsItemVO>
    private lateinit var recyclerviewAdapter:VNewsListAdapter
    private var type:String? = null

    companion object {
        val FRAGMENT_TYPE_PARME = "FRAGMENT_TYPE_PARME"
        val FRAGMENT_ITEM_TITLE = "FRAGMENT_ITEM_TITLE"
        val FRAGMENT_ITEM_BODYID = "FRAGMENT_ITEM_BODYID"
        val TAG = VNewsListFragment::class.java.simpleName
        fun newInstance(param:String): VNewsListFragment {
            var fragment = VNewsListFragment()
            var args = Bundle()
            args.putString(FRAGMENT_TYPE_PARME, param)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = ArrayList<VNewsItemVO>()
        recyclerviewAdapter =   VNewsListAdapter(list)
        recyclerviewAdapter.openLoadAnimation()
        var emptyView = View.inflate(mContext,R.layout.empty_list,null)
        recyclerviewAdapter.setEmptyView(emptyView)
        type = arguments?.getString(FRAGMENT_TYPE_PARME)
        Log.d(TAG, "type init "+ type)
    }

    override fun bindLayout(): Int {
        return R.layout.fragment_list;
    }
    override fun initPresenter(): VNewsListPresenterImpl? {
        return VNewsListPresenterImpl(this)
    }

    override fun initView() {
        recyclerview.setLayoutManager(LinearLayoutManager(mContext))
        recyclerview.addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))
        recyclerview.adapter = recyclerviewAdapter
        Log.d(TAG, "present init "+ (presenter !=null).toString())
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setRefreshing(true)
        onRefresh()
        //presenter?.getVNewsListPresenter(type = text)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        addListener()

    }

    private fun addListener(){
        if (null != recyclerviewAdapter){
            recyclerviewAdapter.onItemClickListener = this
            recyclerviewAdapter.emptyView.setOnClickListener(this)
            recyclerviewAdapter.setOnLoadMoreListener( this,recyclerview)
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearAll()
    }
    private fun clearAll(){
        type = null
    }
    override fun onDetach() {
        super.onDetach()
    }

    //下拉刷新
    override fun onRefresh() {
        Log.d(TAG,"点击了onRefresh")
        recyclerviewAdapter.setEnableLoadMore(false)//这里的作用是防止下拉刷新的时候还可以上拉加载
        pageNum = 1
        recyclerview.postDelayed(object : Runnable{
            override fun run() {
                presenter?.getVNewsListPresenter(type)
            }
        },1000)
    }
    //上拉加载更多
    override fun onLoadMoreRequested() {
        Log.d(TAG,"点击了OnLoadMoreListene")
        pageNum++
        //var type = arguments?.getString(FRAGMENT_TYPE_PARME)
        presenter?.getVNewsListMorePresenter(type,pageNum)
    }

    override fun onFailed(message: String?) {
        if (recyclerviewAdapter.isLoadMoreEnable) {
            recyclerviewAdapter.loadMoreFail()
            recyclerview.postDelayed(object : Runnable {
                override fun run() {
                    if(VNewsUtil.UNCONNECT.equals(message)){
                        recyclerviewAdapter.setEnableLoadMore(false)
                    }
                }
            },1000)
        }
        if (swipeRefreshLayout.isRefreshing){
            Log.d(TAG,"releaseView isRefreshing")
            swipeRefreshLayout.isRefreshing = false
        }
        if (null != message) {
            ToastUtil.showShort(message)
        }
    }

    override fun onSuccess(data: List<VNewsItemVO>?) {
        var size = recyclerviewAdapter.data.size
        if (size > 0){
            recyclerviewAdapter.data.clear()
        }
        if (null != data) {
            recyclerviewAdapter.loadMoreComplete()
            recyclerviewAdapter.setNewData(data)
            recyclerviewAdapter.setEnableLoadMore(true)
        }
    }

    override fun updateRecyclerView(data: List<VNewsItemVO>?) {
        if (null != data) {
            recyclerviewAdapter.loadMoreComplete()
            recyclerviewAdapter.data.addAll(data)
            recyclerviewAdapter.setEnableLoadMore(true)
        }
    }

    override fun releaseView() {
        Log.d(TAG,"releaseView")
        if (swipeRefreshLayout.isRefreshing){
            Log.d(TAG,"releaseView isRefreshing")
            swipeRefreshLayout.isRefreshing = false
        }
    }

    //    //点击条目的操作
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        Log.d(TAG,"第 $position 个")
        var intent = Intent(mContext, DetailActivity::class.java)
        Log.d(TAG, "type onItemClick "+ type)
        intent.putExtra(FRAGMENT_TYPE_PARME,type)
        recyclerviewAdapter.data.get(position).getTitle()?.let { intent.putExtra(FRAGMENT_ITEM_TITLE,it) }
        recyclerviewAdapter.data.get(position).getDocId()?.let {  intent.putExtra(FRAGMENT_ITEM_BODYID,it) }
        ActivityUtils.startActivity(intent)
    }
    //点击空数据时相关操作
    override fun onClick(v: View?) {
        Log.d(TAG,"点击了空视图")
        presenter?.getVNewsListPresenter(type)
    }
}