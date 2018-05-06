package com.xiuyuan.vnews.bean

/**
 * @author Created by xiuyaun
 * @time on 2018/4/23
 */
class MessageEvent {

    private var message: ArrayList<VNewsItemVO>
    private var index : Int = -1
    private var txt:String
    constructor(txt:String,index :Int, message: ArrayList<VNewsItemVO>){
        this.index = index
        this.txt = txt
        this.message = message
    }
    fun setMessage(txt:String,index :Int,message: ArrayList<VNewsItemVO>){
        this.index = index
        this.txt = txt
        this.message = message
    }
    fun getIndex():Int{
        return index
    }
    fun getTxt():String{
        return txt
    }
    fun getMessage():List<VNewsItemVO>{
        return message
    }

}