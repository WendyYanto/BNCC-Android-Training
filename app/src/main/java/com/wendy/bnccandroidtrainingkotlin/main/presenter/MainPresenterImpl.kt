package com.wendy.bnccandroidtrainingkotlin.main.presenter

import com.wendy.bnccandroidtrainingkotlin.main.model.PostResponse
import com.wendy.bnccandroidtrainingkotlin.core.service.PostService
import com.wendy.bnccandroidtrainingkotlin.main.model.PostUiModel
import com.wendy.bnccandroidtrainingkotlin.main.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenterImpl constructor(
  private var view: MainView?,
  private val postService: PostService
) : MainPresenter {

  private var count = 0
  private var postByIdCall: Call<PostResponse>? = null
  private var postsAll: Call<List<PostResponse>>? = null

  override fun buttonOnClick() {
    this.count++
    if (this.count == 2) {
      view?.goToOtherActivity("Go To Other Activity With $count")
    } else {
      view?.showToast("This Is from Presenter Called $count")
    }
  }

  override fun resetCounter() {
    this.count = 0
  }

  override fun detach() {
    postByIdCall?.cancel()
    postsAll?.cancel()
    this.view = null
  }

  override fun getPostById(id: Int) {
    postByIdCall = postService.getPostById(id)
    view?.showLoadingDialog()
    postByIdCall?.enqueue(object : Callback<PostResponse> {
      override fun onFailure(call: Call<PostResponse>, t: Throwable) {
        view?.dismissLoadingDialog()
        println("Error")
      }

      override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
        val postResponse = response.body()
        view?.dismissLoadingDialog()
        postResponse?.let {
          
        } ?: run {
          println("Error")
        }
      }
    })
  }


  override fun getPosts() {
    postsAll = postService.getPosts()
    view?.showLoadingDialog()
    postsAll?.enqueue(object : Callback<List<PostResponse>> {
      override fun onResponse(call: Call<List<PostResponse>>, response: Response<List<PostResponse>>) {
        val responseList = response.body()
        val postsList = mutableListOf<PostUiModel>()
        view?.dismissLoadingDialog()
        responseList?.forEach { postResponse ->
          val postUiModel = PostUiModel()
          postUiModel.title = postResponse.title.orEmpty()
          postUiModel.body = postResponse.body.orEmpty()
          postsList.add(postUiModel)
        }
        view?.populatePosts(postsList)
      }

      override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
        view?.dismissLoadingDialog()
        view?.showToast("System Error")
      }

    })
  }

}