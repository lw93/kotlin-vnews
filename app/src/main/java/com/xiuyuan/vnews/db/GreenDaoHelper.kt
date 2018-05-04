package com.xiuyuan.vnews.db

import greendao.DaoSession
import greendao.DaoMaster
import android.database.sqlite.SQLiteDatabase
import com.xiuyuan.vnews.base.VNewsApplication
import com.xiuyuan.vnews.utils.VNewsUtil


/**
 * @author Created by xiuyaun
 * @time on 2018/5/1
 */
internal class GreenDaoHelper {

    private object SingleTon{
        val INSTANCE = GreenDaoHelper()
    }
    companion object {
        val TAG = GreenDaoHelper::class.java.simpleName
        private var mHelper: DaoMaster.DevOpenHelper? = null
        private var db: SQLiteDatabase? = null
        private var mDaoMaster: DaoMaster? = null
        private var mDaoSession: DaoSession? = null
        val INSTANCE: GreenDaoHelper = SingleTon.INSTANCE
        /**
         * 初始化greenDao，这个操作建议在Application初始化的时候添加；
         */
        fun initDatabase(){
            // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
            // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
            // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
            // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
            mHelper = DaoMaster.DevOpenHelper(VNewsApplication.vNewsContext, VNewsUtil.DB, null)
            db = mHelper?.getWritableDatabase()
            // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
            mDaoMaster = DaoMaster(db)
            mDaoSession = mDaoMaster?.newSession()
        }


    }
    fun getDaoSession(): DaoSession? {
        return mDaoSession
    }
    fun getDB():SQLiteDatabase?{
        return db
    }
}