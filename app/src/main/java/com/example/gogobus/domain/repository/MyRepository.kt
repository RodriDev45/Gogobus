package com.example.gogobus.domain.repository

import com.example.gogobus.domain.model.Post

interface MyRepository {
    suspend fun getPosts(): List<Post>
}