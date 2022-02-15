package com.example.postscaching_jan10.view.comments_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postscaching_jan10.databinding.FragmentCommentBinding
import com.example.postscaching_jan10.utils.DataState
import com.example.postscaching_jan10.view.adapter.CommentAdapter
import com.example.postscaching_jan10.viewmodel.CommentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : Fragment() {

    private var _binding: FragmentCommentBinding? = null
    private val binding: FragmentCommentBinding get() = _binding!!
    private val viewModel: CommentViewModel by viewModels()
    private val commentAdapter by lazy {
        CommentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            commentRv.apply {
                adapter = commentAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
            viewModel.comments.observe(viewLifecycleOwner) { state ->
                updateUi(state is DataState.Loading)
                when (state) {
                    is DataState.Loading -> {
                        // no-op
                    }
                    is DataState.Success -> {
                        commentAdapter.submitList(state.data)
                    }
                    is DataState.Error -> {
                        Toast.makeText(requireContext(), state.msg, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun updateUi(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
