package com.example.postscaching_jan10.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postscaching_jan10.data.local.dao.CommentDao
import com.example.postscaching_jan10.data.local.entity.Comment

@Database(entities = [Comment::class], version = 1, exportSchema = true)
abstract class CommentDatabase : RoomDatabase() {

    abstract fun getCommentDao(): CommentDao
}