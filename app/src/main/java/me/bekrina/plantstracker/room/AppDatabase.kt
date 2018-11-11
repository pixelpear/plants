package me.bekrina.plantstracker.room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import me.bekrina.plantstracker.model.Converters
import me.bekrina.plantstracker.model.Plant
import me.bekrina.plantstracker.model.Watering
import javax.inject.Inject

@Database(entities = [Plant::class, Watering::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    @Inject lateinit var instance : AppDatabase
    abstract fun plantDao(): PlantDao

    private val roomDatabaseCallback = object : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

        }
    }
}
