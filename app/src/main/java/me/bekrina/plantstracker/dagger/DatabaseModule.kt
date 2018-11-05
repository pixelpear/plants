package me.bekrina.plantstracker.dagger

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import me.bekrina.plantstracker.utility.App
import me.bekrina.plantstracker.utility.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Inject lateinit var applicationContext: App
    val DATABASE_NAME = "Local Database"
    lateinit var database: AppDatabase

    @Provides
    @Singleton
    fun injectDatabase(): AppDatabase {
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
        return database
    }
}