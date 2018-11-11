package me.bekrina.plantstracker.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import me.bekrina.plantstracker.model.Converters
import me.bekrina.plantstracker.model.Plant
import me.bekrina.plantstracker.model.Watering

@Database(entities = [Plant::class, Watering::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun plantDao(): PlantDao
}
