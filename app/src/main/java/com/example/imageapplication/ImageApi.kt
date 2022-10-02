package com.example.imageapplication

import com.example.imageapplication.model.PhotoList
import com.example.imageapplication.model.search.Results
import com.example.imageapplication.model.search.Search
import retrofit2.Response
import retrofit2.http.*

interface ImageApi {

    // This is to load random images from 1st page.
    @Headers("Accept-Version: v1",
        "Authorization: Client-ID " + BuildConfig.unsplash_api_key)
    @GET("/photos")
    suspend fun getImages(): Response<List<PhotoList>>

    @Headers("Accept-Version: v1",
        "Authorization: Client-ID " + BuildConfig.unsplash_api_key)
    @GET("/search/photos")
    suspend fun getSearchedImages(@Query("query") imageSearched: String): Response<Search>


}