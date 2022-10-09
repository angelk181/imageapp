package com.example.imageapplication.domain.usecase

import coil.network.HttpException
import com.example.imageapplication.common.ViewState
import com.example.imageapplication.data.remote.dto.toResults
import com.example.imageapplication.domain.model.Results
import com.example.imageapplication.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetResultsUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    operator fun invoke(imageSearched: String): Flow<ViewState<List<Results>>> = flow {
        try {
            emit(ViewState.Loading<List<Results>>())
            val searchedImage = repository.getSearchedImages(imageSearched).toResults()
            emit(ViewState.Success<List<Results>>(searchedImage))
        } catch (e: HttpException) {
            emit(ViewState.Error<List<Results>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(ViewState.Error<List<Results>>("couldnt reach server. check internet connection"))

        }
    }


}