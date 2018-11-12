package com.alenabekrina.plants.repository

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.alenabekrina.plants.model.Plant
import com.alenabekrina.plants.room.AppDatabase
import com.alenabekrina.plants.room.PlantDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(database: AppDatabase){
    val plantDao = database.plantDao()

    init {
        // TODO: Delete. This is needed only temporarily for testing purposes
        PopulateDbAsync(plantDao).execute()
    }

    fun insertPlant(plant: Plant) {
        plantDao.insertPlant(plant)
    }

    fun deletePlant(plant: Plant) {
        plantDao.delete(plant)
    }

    fun getAllPlantsNameDesc(): LiveData<List<Plant>> {
        return GetAllPlantsNameDescAsynkTask(plantDao).execute().get()
    }

    private class GetAllPlantsNameDescAsynkTask internal constructor(private val plantDao: PlantDao) :
        AsyncTask<Any, Any, LiveData<List<Plant>>>() {

        override fun doInBackground(vararg params: Any?): LiveData<List<Plant>> {
            return plantDao.getAllPlantsNameDesc()
        }
    }

    // TODO: Delete. This is needed only temporarily for testing purposes
    private class PopulateDbAsync internal constructor(val dao: PlantDao) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {

            val plant = Plant()
            plant.name = "Kate"
            plant.type = "Palm"
            plant.wateringPeriod = 2
            dao.insertPlant(plant)

            return null
        }
    }
}