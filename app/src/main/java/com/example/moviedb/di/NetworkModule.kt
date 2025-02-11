package com.example.moviedb.di

import com.example.moviedb.data.remote.RetrofitClient
import com.example.moviedb.data.remote.api.PeopleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitClient.instance
    }

    @Provides
    @Singleton
    fun providePeopleApi(retrofit: Retrofit): PeopleApi {
        return retrofit.create(PeopleApi::class.java)
    }
}
