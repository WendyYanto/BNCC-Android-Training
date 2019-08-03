package com.wendy.bnccandroidtrainingkotlin.main.presenter

import com.wendy.bnccandroidtrainingkotlin.main.model.PostResponse
import com.wendy.bnccandroidtrainingkotlin.core.service.PostService
import com.wendy.bnccandroidtrainingkotlin.main.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenterImpl constructor(private var view: MainView?, private val postService: PostService) : MainPresenter {

    private var count = 0
    private var postCall: Call<PostResponse>? = null

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
        postCall?.cancel()
        this.view = null
    }

    override fun getPost(id: Int) {
        postCall = postService.getPosts(id)
        view?.showLoadingDialog()
        postCall?.enqueue(object : Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                view?.dismissLoadingDialog()
                println("Error")
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val postResponse = response.body()
                view?.dismissLoadingDialog()
                postResponse?.let {
                    view?.showPost(it.title.orEmpty(), it.body.orEmpty())
                } ?: run {
                    println("Error")
                }
            }
        })
    }

}