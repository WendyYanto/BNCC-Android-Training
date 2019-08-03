package com.wendy.bnccandroidtrainingkotlin.main.presenter

interface MainPresenter {
    fun buttonOnClick()
    fun resetCounter()
    fun detach()
    fun getPost(id: Int)
}