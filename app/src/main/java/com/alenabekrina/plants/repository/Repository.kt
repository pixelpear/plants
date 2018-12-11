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
    private val plantDao = database.plantDao()

    fun insertPlant(plant: Plant) {
        InsertPlantAsynkTask(plantDao).execute(plant)
    }

    private class InsertPlantAsynkTask internal constructor(private val plantDao: PlantDao):
        AsyncTask<Plant, Any, Unit>() {
        override fun doInBackground(vararg params: Plant) {
            return plantDao.insertPlant(params[0])
        }
    }

    fun deletePlant(plant: Plant) {
        DeletePlantAsynkTask(plantDao).execute(plant)
    }

    private class DeletePlantAsynkTask internal constructor(private val plantDao: PlantDao):
        AsyncTask<Plant, Any, Unit>() {
        override fun doInBackground(vararg params: Plant) {
            return plantDao.delete(params[0])
        }
    }

    fun getAllPlantsNameDesc(): LiveData<List<Plant>> {
        return GetAllPlantsNameDescAsynkTask(plantDao).execute().get()
    }

    private class GetAllPlantsNameDescAsynkTask internal constructor(private val plantDao: PlantDao):
        AsyncTask<Any, Any, LiveData<List<Plant>>>() {

        override fun doInBackground(vararg params: Any?): LiveData<List<Plant>> {
            return plantDao.getAllPlantsNameDesc()
        }
    }
}