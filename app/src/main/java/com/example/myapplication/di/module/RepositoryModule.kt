package com.example.myapplication.di.module

import com.example.myapplication.apiRepository.MainRepository
import com.example.myapplication.apiService.MainApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Dunghv
 * khi them ApiService phai them vao
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

//    @Binds
//    fun provideMainRepositoryImpl(repository: MainRepositoryImpl): MainRepository

    @Provides
    @ViewModelScoped
    fun provideDetailRepository(apiService: MainApiService): MainRepository {
        return MainRepository(apiService)
    }
}