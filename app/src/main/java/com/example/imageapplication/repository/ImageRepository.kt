package com.example.imageapplication.repository

import com.example.imageapplication.ImageApi
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val api: ImageApi) {

    suspend fun getImages() = api.getImages()

    suspend fun getSearchedImages(searchedImage: String) = api.getSearchedImages(searchedImage)
}