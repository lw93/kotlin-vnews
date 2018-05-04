package com.xiuyuan.vnews.db;

import com.xiuyuan.vnews.utils.DateUtil;
import com.xiuyuan.vnews.utils.VNewsUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import greendao.DaoSession;
import greendao.VNewsEntityDao;

/**
 * @author Created by xiuyaun
 * @time on 2018/5/1
 */
@Entity(nameInDb = "vnews",active = true)
public class VNewsEntity {

    @Id(autoincrement = true)
    private Long id;
    @NotNull
    @Property(nameInDb = "idx_news_type")
    private String vnewsType;
    @NotNull
    @Property(nameInDb = "idx_title")
    private String vnewsTitle;
    @NotNull
    @Property(nameInDb = "idx_short_content")
    private String vnewsShortContent;
    @NotNull
    @Property(nameInDb = "img_url")
    private String vnewsImgUrl;

    /**
     * 相关新闻图片地址
     */
    @Property(nameInDb = "img_extra")
    private String vnewsImgExtra;
    @NotNull
    @Property(nameInDb = "news_resouce")
    private String vnewsResouce;

    /**
     * 短文对应新闻内容的docid标识
     */
    @NotNull
    @Unique
    @Property(nameInDb = "uk_docid")
    private String vnewsDocId;


    /**
     * 相关新闻关键字
     */
    @Property(nameInDb = "relative_key")
    private String vnewsRelativeKey;

    @NotNull
    @Property(nameInDb = "idx_body")
    private String vnewsBody;

    @Property(nameInDb = "editor")
    private String vnewsEditor;

    /**
     * 整条记录创建时间
     */
    @NotNull
    @Property(nameInDb = "gmt_create")
    private Date vnewsCreate = Calendar.getInstance().getTime();

    /**
     * 更新时间
     */
    @NotNull
    @Property(nameInDb = "gmt_modified")
    private Date vnewsModified = Calendar.getInstance().getTime();
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 112646543)
    private transient VNewsEntityDao myDao;


    @Generated(hash = 1908302387)
    public VNewsEntity(Long id, @NotNull String vnewsType, @NotNull String vnewsTitle,
            @NotNull String vnewsShortContent, @NotNull String vnewsImgUrl,
            String vnewsImgExtra, @NotNull String vnewsResouce, @NotNull String vnewsDocId,
            String vnewsRelativeKey, @NotNull String vnewsBody, String vnewsEditor,
            @NotNull Date vnewsCreate, @NotNull Date vnewsModified) {
        this.id = id;
        this.vnewsType = vnewsType;
        this.vnewsTitle = vnewsTitle;
        this.vnewsShortContent = vnewsShortContent;
        this.vnewsImgUrl = vnewsImgUrl;
        this.vnewsImgExtra = vnewsImgExtra;
        this.vnewsResouce = vnewsResouce;
        this.vnewsDocId = vnewsDocId;
        this.vnewsRelativeKey = vnewsRelativeKey;
        this.vnewsBody = vnewsBody;
        this.vnewsEditor = vnewsEditor;
        this.vnewsCreate = vnewsCreate;
        this.vnewsModified = vnewsModified;
    }

    @Generated(hash = 1820649911)
    public VNewsEntity() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnewsType() {
        return vnewsType;
    }

    public void setVnewsType(String vnewsType) {
        this.vnewsType = vnewsType;
    }

    public String getVnewsTitle() {
        return vnewsTitle;
    }

    public void setVnewsTitle(String vnewsTitle) {
        this.vnewsTitle = vnewsTitle;
    }

    public String getVnewsShortContent() {
        return vnewsShortContent;
    }

    public void setVnewsShortContent(String vnewsShortContent) {
        this.vnewsShortContent = vnewsShortContent;
    }

    public String getVnewsImgUrl() {
        return vnewsImgUrl;
    }

    public void setVnewsImgUrl(String vnewsImgUrl) {
        this.vnewsImgUrl = vnewsImgUrl;
    }

    public String getVnewsImgExtra() {
        return vnewsImgExtra;
    }

    public void setVnewsImgExtra(String vnewsImgExtra) {
        this.vnewsImgExtra = vnewsImgExtra;
    }

    public String getVnewsResouce() {
        return vnewsResouce;
    }

    public void setVnewsResouce(String vnewsResouce) {
        this.vnewsResouce = vnewsResouce;
    }

    public String getVnewsDocId() {
        return vnewsDocId;
    }

    public void setVnewsDocId(String vnewsDocId) {
        this.vnewsDocId = vnewsDocId;
    }

    public String getVnewsRelativeKey() {
        return vnewsRelativeKey;
    }

    public void setVnewsRelativeKey(String vnewsRelativeKey) {
        this.vnewsRelativeKey = vnewsRelativeKey;
    }

    public String getVnewsBody() {
        return vnewsBody;
    }

    public void setVnewsBody(String vnewsBody) {
        this.vnewsBody = vnewsBody;
    }

    public String getVnewsEditor() {
        return vnewsEditor;
    }

    public void setVnewsEditor(String vnewsEditor) {
        this.vnewsEditor = vnewsEditor;
    }

    public Date getVnewsCreate() {
        return vnewsCreate;
    }

    public void setVnewsCreate(Date vnewsCreate) {
        this.vnewsCreate = vnewsCreate;
    }

    public Date getVnewsModified() {
        return vnewsModified;
    }

    public void setVnewsModified(Date vnewsModified) {
        this.vnewsModified = vnewsModified;
    }

    @Override
    public String toString() {
        return "VNewsEntity{" +
                "id=" + id +
                ", vnewsType='" + vnewsType + '\'' +
                ", vnewsTitle='" + vnewsTitle + '\'' +
                ", vnewsShortContent='" + vnewsShortContent + '\'' +
                ", vnewsImgUrl='" + vnewsImgUrl + '\'' +
                ", vnewsImgExtra='" + vnewsImgExtra + '\'' +
                ", vnewsResouce='" + vnewsResouce + '\'' +
                ", vnewsDocId='" + vnewsDocId + '\'' +
                ", vnewsRelativeKey='" + vnewsRelativeKey + '\'' +
                ", vnewsBody='" + vnewsBody + '\'' +
                ", vnewsEditor='" + vnewsEditor + '\'' +
                ", vnewsCreate=" + vnewsCreate +
                ", vnewsModified=" + vnewsModified +
                '}';
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1444098642)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getVNewsEntityDao() : null;
    }
}
