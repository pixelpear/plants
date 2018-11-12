package com.alenabekrina.plants.dagger

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import com.alenabekrina.plants.room.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    private val DATABASE_NAME = "Local Database"

    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
    }
}