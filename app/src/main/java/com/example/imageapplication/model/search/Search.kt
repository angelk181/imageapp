package com.example.imageapplication.model.search

data class Search(
    val results: List<Results>,
    val total: Int,
    val total_pages: Int
)