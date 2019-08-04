package com.wendy.bnccandroidtrainingkotlin.main.presenter

interface MainPresenter {
    fun buttonOnClick()
    fun resetCounter()
    fun detach()
    fun getPostById(id: Int)
    fun getPosts()
}