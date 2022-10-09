package com.example.imageapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.imageapplication.adapter.ImageAdapter
import com.example.imageapplication.databinding.ImageFragmentBinding
import com.example.presentation.viewmodel.ImageViewModel
import com.example.presentation.SearchedImageState

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.image_fragment) {

    private var _binding: ImageFragmentBinding? = null
    private val binding: ImageFragmentBinding get() = _binding!!

    private val viewModel by viewModels<ImageViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ImageAdapter()
        binding.rvImages.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchedImage.collect { state ->
                    when (state) {
                        SearchedImageState(isLoading = state.isLoading) -> {
                            // shimmer will be set here
                        }
                        SearchedImageState(results = state.results) -> {
                            state.results?.let { adapter.setSearchedImageList(it) }
                        }
                        SearchedImageState(error = state.error) -> {

                            // snack bar for errors will be added and handled with a single event variable
                        }
                    }
                }
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ImageFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}