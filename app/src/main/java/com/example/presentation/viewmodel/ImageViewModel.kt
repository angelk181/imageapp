package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageapplication.common.Constants.searchExample
import com.example.imageapplication.common.ViewState
import com.example.imageapplication.domain.usecase.GetResultsUseCase
import com.example.presentation.SearchedImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val getResultsUseCase: GetResultsUseCase
) : ViewModel() {
    private val _searchedImages = MutableStateFlow(SearchedImageState())
    val searchedImage = _searchedImages as StateFlow<SearchedImageState>


    init {
        getSearchedImages()
    }

    private fun getSearchedImages() {
        getResultsUseCase(searchExample).onEach { result ->

            when(result) {
                is ViewState.Success -> {
                    _searchedImages.value = SearchedImageState(results = result.data)
                }
                is ViewState.Error -> {
                    _searchedImages.value = SearchedImageState(error = result.message ?: "Unexpected error occurred")
                }
                is ViewState.Loading -> {
                    _searchedImages.value = SearchedImageState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}