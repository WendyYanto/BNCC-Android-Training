package com.wendy.bnccandroidtrainingkotlin.main.view

import com.wendy.bnccandroidtrainingkotlin.main.model.PostUiModel

interface MainView {
  fun showToast(text: String)
  fun goToOtherActivity(content: String)
  fun showLoadingDialog()
  fun dismissLoadingDialog()
  fun populatePosts(lists: List<PostUiModel>)
}