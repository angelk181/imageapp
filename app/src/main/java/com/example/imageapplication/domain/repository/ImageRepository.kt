package com.example.imageapplication.domain.repository

import com.example.imageapplication.domain.model.PhotoList
import com.example.imageapplication.data.remote.dto.SearchDto
import com.example.imageapplication.domain.model.Results
import retrofit2.Response

interface ImageRepository {

    suspend fun getImages(): Response<List<PhotoList>>

    suspend fun getSearchedImages(imageSearched: String): SearchDto

}