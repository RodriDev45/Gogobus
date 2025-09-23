package com.example.gogobus.data.remote

import com.example.gogobus.domain.model.Post
import retrofit2.http.GET

interface GogobusApi {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}