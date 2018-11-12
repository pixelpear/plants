package com.alenabekrina.plants.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.alenabekrina.plants.model.Converters
import com.alenabekrina.plants.model.Plant
import com.alenabekrina.plants.model.Watering

@Database(entities = [Plant::class, Watering::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun plantDao(): PlantDao
}
