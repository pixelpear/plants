package me.bekrina.plantstracker.dagger

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import me.bekrina.plantstracker.utility.App
import me.bekrina.plantstracker.utility.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {
    val DATABASE_NAME = "Local Database"

    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
    }
}