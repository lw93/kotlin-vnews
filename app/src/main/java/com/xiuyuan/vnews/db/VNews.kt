package com.xiuyuan.vnews.db

import org.greenrobot.greendao.annotation.*
import java.sql.Timestamp
import java.util.*

/**
 * @author Created by xiuyuan
 * @time on 2018/4/18
 */
@Entity(nameInDb = "vnews")
internal class VNews {
    @Id(autoincrement = true)
    private var id: Long = 0
    @NotNull
    @Property(nameInDb = "idx_news_type")
    private var vnewsType: String? = null
    @NotNull
    @Property(nameInDb = "idx_title")
    private var vnewsTitle: String? = null
    @NotNull
    @Property(nameInDb = "idx_short_content")
    private var vnewsShortContent: String? = null
    @NotNull
    @Property(nameInDb = "img_url")
    private var vnewsImgUrl: String? = null

    /**
     * 相关新闻图片地址
     */
    @Property(nameInDb = "img_extra")
    private var vnewsImgExtra: String? = null
    @NotNull
    @Property(nameInDb = "news_resouce")
    private var vnewsResouce: String? = null

    /**
     * 短文对应新闻内容的docid标识
     */
    @NotNull
    @Unique
    @Property(nameInDb = "uk_docid")
    private var vnewsDocId: String? = null


    /**
     * 相关新闻关键字
     */
    @Property(nameInDb = "relative_key")
    private var vnewsRelativeKey: String? = null

    @NotNull
    @Property(nameInDb = "idx_body")
    private var vnewsBody: String? = null

    @Property(nameInDb = "editor")
    private var vnewsEditor: String? = null

    /**
     * 整条记录创建时间
     */
    @NotNull
    @Property(nameInDb = "gmt_create")
    private var vnewsCreate: Timestamp = Timestamp(Calendar.getInstance().timeInMillis)

    /**
     * 更新时间
     */
    @NotNull
    @Property(nameInDb = "gmt_modified")
    private var vnewsModified: Timestamp = Timestamp(Calendar.getInstance().timeInMillis)


    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getVnewsType(): String?{
        return vnewsType
    }

    fun setVnewsType(vnewsType: String) {
        this.vnewsType = vnewsType
    }

    fun getVnewsTitle(): String? {
        return vnewsTitle
    }

    fun setVnewsTitle(vnewsTitle: String) {
        this.vnewsTitle = vnewsTitle
    }

    fun getVnewsShortContent(): String? {
        return vnewsShortContent
    }

    fun setVnewsShortContent(vnewsShortContent: String) {
        this.vnewsShortContent = vnewsShortContent
    }

    fun getVnewsImgUrl(): String? {
        return vnewsImgUrl
    }

    fun setVnewsImgUrl(vnewsImgUrl: String) {
        this.vnewsImgUrl = vnewsImgUrl
    }

    fun getVnewsImgExtra(): String?{
        return vnewsImgExtra
    }

    fun setVnewsImgExtra(vnewsImgExtra: String) {
        this.vnewsImgExtra = vnewsImgExtra
    }

    fun getVnewsResouce(): String? {
        return vnewsResouce
    }

    fun setVnewsResouce(vnewsResouce: String) {
        this.vnewsResouce = vnewsResouce
    }

    fun getVnewsDocId(): String? {
        return vnewsDocId
    }

    fun setVnewsDocId(vnewsDocId: String) {
        this.vnewsDocId = vnewsDocId
    }

    fun getVnewsRelativeKey(): String? {
        return vnewsRelativeKey
    }

    fun setVnewsRelativeKey(vnewsRelativeKey: String) {
        this.vnewsRelativeKey = vnewsRelativeKey
    }

    fun getVnewsBody(): String? {
        return vnewsBody
    }

    fun setVnewsBody(vnewsBody: String) {
        this.vnewsBody = vnewsBody
    }

    fun getVnewsEditor(): String? {
        return vnewsEditor
    }

    fun setVnewsEditor(vnewsEditor: String) {
        this.vnewsEditor = vnewsEditor
    }

    fun getVnewsCreate(): Timestamp? {
        return vnewsCreate
    }

    fun setVnewsCreate(vnewsCreate: Timestamp) {
        this.vnewsCreate = vnewsCreate
    }

    fun getVnewsModified(): Timestamp? {
        return vnewsModified
    }

    fun setVnewsModified(vnewsModified: Timestamp) {
        this.vnewsModified = vnewsModified
    }


    override fun toString(): String {
        return "VNews(id=$id, vnewsType=$vnewsType, vnewsTitle=$vnewsTitle, vnewsShortContent=$vnewsShortContent, vnewsImgUrl=$vnewsImgUrl, vnewsImgExtra=$vnewsImgExtra, vnewsResouce=$vnewsResouce, vnewsDocId=$vnewsDocId, vnewsRelativeKey=$vnewsRelativeKey, vnewsBody=$vnewsBody, vnewsEditor=$vnewsEditor, vnewsCreate=$vnewsCreate, vnewsModified=$vnewsModified)"
    }


}
