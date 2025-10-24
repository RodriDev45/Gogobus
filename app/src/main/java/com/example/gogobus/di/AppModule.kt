package com.example.gogobus.di

import com.example.gogobus.data.remote.GogobusApi
import com.example.gogobus.data.repository.MyRepositoryImpl
import com.example.gogobus.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): GogobusApi =
        Retrofit.Builder()
            .baseUrl("https://gogobusbackend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GogobusApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: GogobusApi): MyRepository =
        MyRepositoryImpl(api)

}