package com.xiuyuan.vnews.bean

import java.util.Arrays

/**
 * @author Created by xiuyaun
 * @time on 2018/4/18
 */

open class VNewsPageVO<T> {
    private var endRow: Int = 0
    private var firstPage: Int = 0
    private var hasNextPage: Boolean = false
    private var hasPreviousPage: Boolean = false
    private var isFirstPage: Boolean = false
    private var isLastPage: Boolean = false
    private var lastPage: Int = 0
    private var list: List<T>? = null
    private var navigatePages: Int = 0
    private var navigatepageNums: IntArray? = null
    private var nextPage: Int = 0
    private var pageNum: Int = 0
    private var pageSize: Int = 0
    private var pages: Int = 0
    private var prePage: Int = 0
    private var size: Int = 0
    private var startRow: Int = 0
    private var total: Int = 0

    fun setEndRow(endRow : Int){
        this.endRow = endRow
    }
    fun getEndRow(): Int {
        return endRow
    }
    fun setFirstPage(firstPage : Int){
        this.firstPage = firstPage
    }
    fun getFirstPage(): Int{
        return  firstPage
    }
    fun setHasNextPage(hasNextPage : Boolean){
        this.hasNextPage = hasNextPage
    }
    fun getHasNextPage(): Boolean{
        return  hasNextPage
    }
    fun setHasPreviousPage(hasPreviousPage : Boolean){
        this.hasPreviousPage = hasPreviousPage
    }
    fun getHasPreviousPage():Boolean {
        return hasPreviousPage
    }

    fun isFirstPage(): Boolean {
        return isFirstPage
    }

    fun setFirstPage(firstPage: Boolean) {
        isFirstPage = firstPage
    }

    fun isLastPage(): Boolean {
        return isLastPage
    }

    fun setLastPage(lastPage: Boolean) {
        isLastPage = lastPage
    }

    fun getLastPage(): Int {
        return lastPage
    }

    fun setLastPage(lastPage: Int) {
        this.lastPage = lastPage
    }
    fun setList(list : List<T>){
        this.list = list
    }
    fun getList(): List<T>? {
        return list
    }
    fun setNavigatePages(navigatePages : Int){
        this.navigatePages = navigatePages
    }
    fun getNavigatePages(): Int {
        return navigatePages
    }
    fun setNavigatepageNums(navigatepageNums : IntArray){
        this.navigatepageNums = navigatepageNums
    }
    fun getNavigatepageNums(): IntArray? {
        return navigatepageNums
    }
    fun setNextPage(nextPage : Int) {
        this.nextPage = nextPage
    }
    fun getNextPage(): Int {
        return nextPage
    }

    fun setPageNum(pageNum : Int) {
        this.pageNum = pageNum
    }
    fun getPageNum(): Int {
        return pageNum
    }
    fun setPageSize(pageSize : Int) {
        this.pageSize = pageSize
    }
    fun getPageSize(): Int {
        return pageSize
    }fun setPages(pages : Int) {
        this.pages = pages
    }
    fun getPages(): Int {
        return pages
    }fun setPrePage(prePage : Int) {
        this.prePage = prePage
    }
    fun getPrePage(): Int {
        return prePage
    }
    fun setize(size : Int) {
        this.size = size
    }
    fun getSize(): Int {
        return size
    }
    fun setStartRow(startRow : Int) {
        this.startRow = startRow
    }
    fun getStartRow(): Int {
        return startRow
    }
    fun setTotal(total : Int) {
        this.total = total
    }
    fun getTotal(): Int {
        return total
    }
    override fun toString(): String {
        return "VNewsPageVO(endRow=$endRow, firstPage=$firstPage, hasNextPage=$hasNextPage, hasPreviousPage=$hasPreviousPage, isFirstPage=$isFirstPage, isLastPage=$isLastPage, lastPage=$lastPage, list=$list, navigatePages=$navigatePages, navigatepageNums=${Arrays.toString(navigatepageNums)}, nextPage=$nextPage, pageNum=$pageNum, pageSize=$pageSize, pages=$pages, prePage=$prePage, size=$size, startRow=$startRow, total=$total)"
    }

}
