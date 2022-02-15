package com.example.postscaching_jan10.di

import android.content.Context
import androidx.room.Room
import com.example.postscaching_jan10.data.local.CommentDatabase
import com.example.postscaching_jan10.data.local.dao.CommentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesCommentDatabase(
        @ApplicationContext context: Context
    ): CommentDatabase {
        return Room.databaseBuilder(
            context,
            CommentDatabase::class.java,
            "comment_database"
        ).build()
    }

    @Provides
    fun providesCommentDao(database: CommentDatabase): CommentDao {
        return database.getCommentDao()
    }
}