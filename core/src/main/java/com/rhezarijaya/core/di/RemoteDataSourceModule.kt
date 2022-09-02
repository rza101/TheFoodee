package com.rhezarijaya.core.di

import com.rhezarijaya.core.data.remote.network.APIService
import com.rhezarijaya.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        // TODO check jika build debug atau release
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    fun provideAPIService(client: OkHttpClient): APIService {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(APIService::class.java)
    }
}