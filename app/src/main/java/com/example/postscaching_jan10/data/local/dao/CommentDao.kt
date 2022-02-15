package com.example.postscaching_jan10.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.postscaching_jan10.data.local.entity.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Insert
    suspend fun insertAllComment(comments: List<Comment>)

    @Query("SELECT * FROM comments")
    suspend fun getAllComments(): List<Comment>

    @Query("SELECT count(*) FROM comments")
    suspend fun getCommentCount(): Int
}