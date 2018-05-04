package com.xiuyuan.vnews.utils

import java.text.DateFormat
import android.text.format.DateFormat.getDateFormat
import android.provider.Settings.System.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author Created by xiuyaun
 * @time on 2018/5/2
 */
internal class DateUtil {
    companion object {
        private val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private val threadLocal = ThreadLocal<DateFormat>()
        fun getDateFormat(): DateFormat {
            var df = threadLocal.get()
            if (df == null) {
                df = SimpleDateFormat(DATE_FORMAT)
                threadLocal.set(df)
            }
            return df
        }

        fun formatDate(date: Date): String {
            val dateFormat = getDateFormat()
            return dateFormat.format(date)
        }


        fun parseDate(strDate: String): Date? {
            val dateFormat = getDateFormat()
            try {
                return dateFormat.parse(strDate)
            }catch (exception:Exception){
                return  null
            }
        }
    }
}