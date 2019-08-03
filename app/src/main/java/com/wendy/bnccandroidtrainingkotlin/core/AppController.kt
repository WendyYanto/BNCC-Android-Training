package com.wendy.bnccandroidtrainingkotlin.core

import com.wendy.bnccandroidtrainingkotlin.core.service.PostService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppController {

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun providePostService(): PostService {
        return this.getRetrofit("https://jsonplaceholder.typicode.com/").create(PostService::class.java)
    }
}