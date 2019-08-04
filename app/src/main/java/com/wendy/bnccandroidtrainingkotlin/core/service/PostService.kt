package com.wendy.bnccandroidtrainingkotlin.core.service

import com.wendy.bnccandroidtrainingkotlin.main.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<PostResponse>

    @GET("/posts")
    fun getPosts(): Call<List<PostResponse>>
}