package com.wendy.bnccandroidtrainingkotlin.core.service

import com.wendy.bnccandroidtrainingkotlin.main.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("/posts/{id}")
    fun getPosts(@Path("id") id: Int): Call<PostResponse>

}