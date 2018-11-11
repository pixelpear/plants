package me.bekrina.plantstracker.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import me.bekrina.plantstracker.model.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY name DESC")
    fun getAllPlantsNameDesc(): LiveData<List<Plant>>

    @Insert
    fun insertPlant(plant: Plant)

    @Delete
    fun delete(plant: Plant)
}
