package com.xiuyuan.vnews.dto

/**
 * @author Created by xiuyaun
 * @time on 2018/4/18
 */

open class VNewsDTO<T> {
    private var code: Int = 0
    private var message: String? = null
    private var data: T? = null
    fun setCode(code : Int) {
        this.code  = code
    }
    fun getCode(): Int {
       return code
    }
    fun setMessage(message : String) {
        this.message = message
    }
    fun getMessage(): String?{
      return  message
    }
    fun setData(data : T) {
        this.data = data
    }
    fun getData(): T? {
       return data
    }

    override fun toString(): String {
        return "VNewsDTO(code=$code, message=$message, data=$data)"
    }
}
