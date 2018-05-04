package com.xiuyuan.vnews.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author Created by xiuyaun
 * @time on 2018/4/18
 */

open class VNewsItemVO: MultiItemEntity{

    private var docId: String? = null
    private var editor: String? = null
    private var imgExtra: String? = null
    private var imgUrl: String? = null
    private var relativeKey: String? = null
    private var resouce: String? = null
    private var shortContent: String? = null
    private var title: String? = null
    private var type: String? = null

    override fun getItemType(): Int {
        return 0
    }
    fun setDocId(docId : String){
        this.docId = docId
    }
    fun getDocId(): String?{
       return docId
    }
    fun setEditor(editor : String){
        this.editor = editor
    }
    fun getEditor(): String? {
        return editor
    }
    fun setImgExtra(imgExtra : String){
        this.imgExtra = imgExtra
    }
    fun getImgExtra(): String? {
        return imgExtra
    }
    fun setimgUrl(imgUrl : String){
        this.imgUrl = imgUrl
    }
    fun getImgUrl(): String? {
        return imgUrl
    }
    fun setRelativeKey(relativeKey : String){
        this.relativeKey = relativeKey
    }
    fun getRelativeKey(): String? {
        return relativeKey
    }
    fun setResouce(resouce : String){
        this.resouce = resouce
    }
    fun getResouce(): String? {
        return resouce
    }
    fun setShortContent(shortContent : String){
        this.shortContent = shortContent
    }
    fun getShortContent(): String? {
        return shortContent
    }
    fun setTitle(title : String){
        this.title = title
    }
    fun getTitle(): String? {
        return title
    }
    fun setType(type : String){
        this.type = type
    }
    fun getType(): String? {
        return type
    }
    override fun toString(): String {
        return "VNewsItemVO(docId=$docId, editor=$editor, imgExtra=$imgExtra, imgUrl=$imgUrl, relativeKey=$relativeKey, resouce=$resouce, shortContent=$shortContent, title=$title, type=$type)"
    }

}
