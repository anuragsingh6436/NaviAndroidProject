package com.example.githubproject.di

import com.example.githubproject.network.ApiHelper
import com.example.githubproject.network.RetrofitBuilder
import com.example.githubproject.repository.MainActivityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
open class MainActivityModule {

    @Provides
    fun provideRepository(apiHelper: ApiHelper): MainActivityRepository {
        return MainActivityRepository(apiHelper)
    }

    @Provides
    fun provideApiHelper(): ApiHelper {
        return ApiHelper(RetrofitBuilder.apiService)
    }
}