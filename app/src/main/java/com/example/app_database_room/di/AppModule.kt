package com.example.app_database_room.di

import android.content.Context
import androidx.room.Room
import com.example.app_database_room.room.CronosDatabase
import com.example.app_database_room.room.CronosDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesCronosDao(cronoDatabase: CronosDatabase) : CronosDatabaseDao {
        return cronoDatabase.cronosDao()
    }

    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context : Context): CronosDatabase {
        return Room.databaseBuilder(
            context,
            CronosDatabase::class.java, "cronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}