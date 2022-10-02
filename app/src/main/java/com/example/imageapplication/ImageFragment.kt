package com.example.imageapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.imageapplication.Util.ViewState
import com.example.imageapplication.adapter.ImageAdapter
import com.example.imageapplication.databinding.ImageFragmentBinding
import com.example.imageapplication.viewmodel.ImageViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.image_fragment) {

    private var _binding: ImageFragmentBinding? = null
    private val binding: ImageFragmentBinding get() = _binding!!

    private val viewModel by viewModels<ImageViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ImageAdapter()
        binding.rvImages.adapter = adapter

        viewModel.searchedImages.observe(viewLifecycleOwner){ response ->
            when(response) {
                is ViewState.Loading -> {

                    // shimmer will be set here
                }
                is ViewState.Success -> {
                    response.data?.let { adapter.setSearchedImageList(it.results) }

                }
                else -> {
                    // snack bar for errors will be added.

                }
            }

        }
        }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ImageFragmentBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}