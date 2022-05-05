package io.michiganelections.api.injection

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.michiganelections.api.service.ApiService
import io.michiganelections.api.service.ApiServiceImpl
import io.michiganelections.api.service.LocalDateAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

  private val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
    Timber.tag("HTTP").d(message)
  }
    .apply {
      level = HttpLoggingInterceptor.Level.BASIC
    }


  @Provides
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .add(LocalDateAdapter())
      .build()
  }

  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(httpLoggingInterceptor)
      .build()
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://michiganelections.io/api/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

  @Provides
  fun provideApiService(apiService: ApiServiceImpl): ApiService {
    return apiService
  }
}
