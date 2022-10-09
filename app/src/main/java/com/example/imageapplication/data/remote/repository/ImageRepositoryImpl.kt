package com.example.imageapplication.data.remote.repository

import com.example.imageapplication.data.remote.ImageApi
import com.example.imageapplication.domain.model.PhotoList
import com.example.imageapplication.data.remote.dto.SearchDto
import com.example.imageapplication.domain.repository.ImageRepository
import retrofit2.Response
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: ImageApi
) : ImageRepository {

    override suspend fun getImages(): Response<List<PhotoList>> {
        return api.getImages()
    }

    override suspend fun getSearchedImages(imageSearched: String): SearchDto{
        return api.getSearchedImages(imageSearched)
    }

}