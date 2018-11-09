package me.bekrina.plantstracker.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY name DESC")
    fun getAllPlantsDesc(): LiveData<List<Plant>>

    @Insert
    fun insertPlant(plant: Plant)
}
