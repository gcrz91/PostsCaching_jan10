package com.example.postscaching_jan10.di

import com.example.postscaching_jan10.data.local.dao.CommentDao
import com.example.postscaching_jan10.data.remote.service.CommentService
import com.example.postscaching_jan10.data.repository.CommentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesCommentRepository(
        commentService: CommentService,
        commentDao: CommentDao
    ): CommentRepository {
        return CommentRepository(commentService, commentDao)
    }
}