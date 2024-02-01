package com.example.datastorecompose.di

import android.content.Context
import com.example.datastorecompose.datastore.DataStoreManager
import com.example.datastorecompose.datastore.DataStoreRepo
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
    fun provideDataStoreRepository(
        @ApplicationContext context: Context,
    ): DataStoreRepo = DataStoreManager(context)

}