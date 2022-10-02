package com.example.imageapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageapplication.Constants.Companion.searchExample
import com.example.imageapplication.Util.ViewState
import com.example.imageapplication.model.PhotoList
import com.example.imageapplication.model.search.Search
import com.example.imageapplication.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {
    private val _images = MutableLiveData<List<PhotoList>>()
    private val _searchedImages = MutableLiveData<ViewState<Search>>()
    val images = _images as LiveData<List<PhotoList>>
    val searchedImages = _searchedImages as LiveData<ViewState<Search>>


    init {
        getImages()
        getSearchedImages()
    }



    private fun getImages() = viewModelScope.launch {
        imageRepository.getImages().let { response ->
            if (response.isSuccessful) {
                _images.postValue(response.body())
            } else {
                Log.d("error", "image error" + response.message())
            }
        }
    }

    private fun getSearchedImages() = viewModelScope.launch {
        imageRepository.getSearchedImages(searchExample).let { response ->

            if (response.isSuccessful) {
                _searchedImages.postValue(ViewState.Success(response.body()))
            } else {
                _searchedImages.postValue(ViewState.Error(response.message(),response.body()))
                Log.d("search image error", response.message())
            }
        }
    }
}