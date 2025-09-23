package com.example.gogobus.data.repository

import com.example.gogobus.data.remote.GogobusApi
import com.example.gogobus.domain.model.Post
import com.example.gogobus.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: GogobusApi
) : MyRepository {

    override suspend fun getPosts(): List<Post> {
        return api.getPosts()
    }
}