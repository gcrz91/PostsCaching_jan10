package com.example.postscaching_jan10.data.remote.models

import com.example.postscaching_jan10.data.local.entity.Comment
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentDto(
    val userId: Int?,
    val id: Int,
    val title: String?,
    val body: String
) {

    // Converts response object to Comment entity
    fun toComment(): Comment {
        return Comment(
            userId = userId ?: 0,
            id = id,
            title = title ?: "No title",
            body = body
        )
    }
}
