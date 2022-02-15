package com.example.postscaching_jan10.data.remote.service

import com.example.postscaching_jan10.data.remote.models.CommentDto
import retrofit2.Response
import retrofit2.http.GET

interface CommentService {

    @GET("posts")
    suspend fun getComments(): Response<List<CommentDto>>
}