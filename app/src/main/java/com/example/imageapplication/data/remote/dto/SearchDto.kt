package com.example.imageapplication.data.remote.dto

import com.example.imageapplication.domain.model.Results

data class SearchDto(
    val results: List<Results>,
    val total: Int,
    val total_pages: Int
)

fun SearchDto.toResults() : List<Results> {

    val results = results.map {
        Results(
            it.blur_hash,
            it.color,
            it.created_at,
            it.current_user_collections,
            it.description,
            it.height,
            it.id,
            it.liked_by_user,
            it.likes,
            it.links,
            it.urls,
            it.user,
            it.width,
        )
    }
    return results
}


