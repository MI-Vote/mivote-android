package com.fueledbycaffeine.mivote.injection

import android.content.Context
import com.fueledbycaffeine.mivote.data.VoterDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

  @Singleton
  @Provides
  fun provideVoterDataStore(@ApplicationContext appContext: Context): VoterDataStore {
    return VoterDataStore(appContext)
  }
}
