package com.example.postscaching_jan10.data.repository

import com.example.postscaching_jan10.data.local.dao.CommentDao
import com.example.postscaching_jan10.data.local.entity.Comment
import com.example.postscaching_jan10.data.remote.service.CommentService
import com.example.postscaching_jan10.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val commentService: CommentService,
    private val commentDao: CommentDao
) {

    fun getComments(): Flow<DataState<List<Comment>>> = flow {
        emit(DataState.Loading)

        val isDbEmpty = commentDao.getCommentCount() == 0

        if (isDbEmpty) {
            val result = try {
                val response = commentService.getComments()
                if (response.isSuccessful && response.body() != null) {
                    val commentList = response.body()!!.map { it.toComment() }
                    // Inserting comments to DB
                    commentDao.insertAllComment(commentList)
                    //
                    DataState.Success(commentList)
                } else {
                    DataState.Error("Could not fetch any comments")
                }
            } catch (ex: Exception) {
                DataState.Error(ex.message ?: "Unexpected error.")
            }
            emit(result)
        } else {
            // If db not empty return db contents
            val dbResult = commentDao.getAllComments()
            emit(DataState.Success(dbResult))
        }
    }
}