package com.example.postscaching_jan10.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postscaching_jan10.data.local.entity.Comment
import com.example.postscaching_jan10.databinding.CommentRowItemBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val commentList = mutableListOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            CommentRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size

    fun submitList(comments: List<Comment>) {
        commentList.clear()
        commentList.addAll(comments)
        notifyDataSetChanged()
    }

    class CommentViewHolder(private val binding: CommentRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) = with(binding) {
            userIdTv.text = comment.userId.toString()
            idTv.text = comment.id.toString()
            titleTv.text = comment.title
            bodyTv.text = comment.body
        }
    }
}