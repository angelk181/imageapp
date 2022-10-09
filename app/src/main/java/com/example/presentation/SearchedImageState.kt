package com.example.presentation

import com.example.imageapplication.common.Constants.EMPTY
import com.example.imageapplication.domain.model.Results

data class SearchedImageState(
val isLoading: Boolean = false,
val results: List<Results>? = null,
val error: String = EMPTY
)

