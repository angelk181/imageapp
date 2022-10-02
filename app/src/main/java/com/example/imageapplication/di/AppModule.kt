package com.example.imageapplication.di

import com.example.imageapplication.Constants.Companion.baseUrl
import com.example.imageapplication.ImageApi
import com.example.imageapplication.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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
    ) = ImageRepository(api)


    @Singleton
    @Provides
    fun provideRetrofit(): ImageApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)

}