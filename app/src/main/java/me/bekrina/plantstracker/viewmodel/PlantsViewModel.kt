package me.bekrina.plantstracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import me.bekrina.plantstracker.model.Plant
import me.bekrina.plantstracker.model.PlantDao
import me.bekrina.plantstracker.utility.AppDatabase
import javax.inject.Inject

class PlantsViewModel: ViewModel() {
    @Inject
    lateinit var database: AppDatabase
    private lateinit var plants: LiveData<List<Plant>>
    private lateinit var plantDao: PlantDao

    init {
        plantDao = database.plantDao()
    }

    fun getPlantsDesc(): LiveData<List<Plant>> {
        if (!::plants.isInitialized) {
            plants = loadPlantsDesc(plantDao)
        }
        return plants
    }

    private class getPlantsAsynkTask internal constructor(private val plantDao: PlantDao) :
        AsyncTask<Any, Any, LiveData<List<Plant>>>() {

        override fun doInBackground(vararg params: Any?): LiveData<List<Plant>> {
            return plantDao.getAllPlantsDesc()
        }
    }

    fun loadPlantsDesc(plantDao: PlantDao): LiveData<List<Plant>> {
        return getPlantsAsynkTask(plantDao).execute().get()
    }
}
