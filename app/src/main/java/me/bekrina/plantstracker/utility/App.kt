package me.bekrina.plantstracker.utility

import android.app.Application
import android.arch.persistence.room.Room


class App: Application() {
    val DATABASE_NAME = "Local Database"
    lateinit var database: AppDatabase
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
    }

    fun getDB(): AppDatabase {
        return database
    }
}