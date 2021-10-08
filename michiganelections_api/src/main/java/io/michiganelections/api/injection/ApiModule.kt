package io.michiganelections.api.injection

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.michiganelections.api.service.ApiService
import io.michiganelections.api.service.ApiServiceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

  @Provides
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .build()
  }

  @Provides
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.tag("HTTP").d(message) }
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return loggingInterceptor
  }

  @Provides
  fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
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
