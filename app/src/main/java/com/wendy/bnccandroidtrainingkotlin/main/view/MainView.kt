package com.wendy.bnccandroidtrainingkotlin.main.view

interface MainView {
    fun showToast(text: String)
    fun goToOtherActivity(content: String)
    fun showLoadingDialog()
    fun dismissLoadingDialog()
    fun showPost(title: String, body: String)
}