package com.demo.covidtracker.di

import android.content.Context
import com.demo.covidtracker.data.services.networkServices.CovidApiService
import com.demo.covidtracker.domain.local.AppDatabase
import com.demo.covidtracker.domain.local.CountryDataDao
import com.demo.covidtracker.domain.remote.CountriesDataRemoteDataSource
import com.demo.covidtracker.domain.repository.CovidRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {

        val okHttpClient = providesOkHttpClient().newBuilder()
        val logging = HttpLoggingInterceptor()
        logging.apply { level = HttpLoggingInterceptor.Level.BODY }
        okHttpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja/v2/")
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCovidApiService(retrofit: Retrofit): CovidApiService =
        retrofit.create(CovidApiService::class.java)

    @Singleton
    @Provides
    fun provideCountriesDataRemoteDataSource(covidApiService: CovidApiService) =
        CountriesDataRemoteDataSource(covidApiService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.countryDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: CountriesDataRemoteDataSource,
        localDataSource: CountryDataDao
    ) = CovidRepository(remoteDataSource, localDataSource)

}