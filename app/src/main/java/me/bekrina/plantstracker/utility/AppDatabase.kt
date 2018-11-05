package me.bekrina.plantstracker.utility

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import me.bekrina.plantstracker.model.Converters
import me.bekrina.plantstracker.model.Plant
import me.bekrina.plantstracker.model.PlantDao
import me.bekrina.plantstracker.model.Watering

@Database(entities = [Plant::class, Watering::class], version = 0)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun plantDao(): PlantDao

    private val roomDatabaseCallback = object : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            //PopulateDbAsync(INSTANCE!!).execute()
        }
    }
}
