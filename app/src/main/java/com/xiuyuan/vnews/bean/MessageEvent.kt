package com.xiuyuan.vnews.bean

/**
 * @author Created by xiuyaun
 * @time on 2018/4/23
 */
internal class MessageEvent {

    private var message: HashMap<String,Any>

    constructor(message: HashMap<String,Any>){
        this.message = message
    }
    fun setMessage(message: HashMap<String,Any>){
        this.message = message
    }
    fun getMessage():HashMap<String,Any>{
        return message
    }

}