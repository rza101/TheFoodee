package com.rhezarijaya.core.di

import android.content.Context
import androidx.room.Room
import com.rhezarijaya.core.data.local.room.FavoriteFoodDAO
import com.rhezarijaya.core.data.local.room.FavoriteFoodDatabase
import com.rhezarijaya.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavoriteFoodDatabase =
        Room.databaseBuilder(
            context,
            FavoriteFoodDatabase::class.java,
            Constants.DATABASE_FILE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(SupportFactory(Constants.DATABASE_CIPHER_PASSWORD.toByteArray()))
            .build()

    @Provides
    fun provideFavoriteFoodDAO(database: FavoriteFoodDatabase): FavoriteFoodDAO =
        database.favoriteFoodDAO()
}