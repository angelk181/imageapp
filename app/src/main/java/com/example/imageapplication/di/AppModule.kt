package com.example.imageapplication.di


import com.example.imageapplication.common.Constants.baseUrl
import com.example.imageapplication.data.remote.ImageApi
import com.example.imageapplication.data.remote.repository.ImageRepositoryImpl
import com.example.imageapplication.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        api: ImageApi
    ): ImageRepository = ImageRepositoryImpl(api)


        @Singleton
        @Provides
        fun provideRetrofit(): ImageApi =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImageApi::class.java)
    }

