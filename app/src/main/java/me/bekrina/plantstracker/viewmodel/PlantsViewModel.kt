package me.bekrina.plantstracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import me.bekrina.plantstracker.model.Plant
import me.bekrina.plantstracker.model.PlantDao
import me.bekrina.plantstracker.utility.App
import org.threeten.bp.OffsetDateTime
import java.util.concurrent.ExecutionException

class PlantsViewModel: ViewModel() {
    private lateinit var plants: MutableLiveData<List<Plant>>
    private lateinit var plantDao: PlantDao

    init {
        // inject database
        plantDao = database.plantDao()
    }

    fun getPlantsDesc(): MutableLiveData<List<Plant>> {
        if (!::plants.isInitialized) {
            plants = MutableLiveData()
            plants = loadPlantsDesc()
        }
        return plants
    }

    private class getFutureEventsAsynkTask internal constructor(private val plantDao: PlantDao) :
        AsyncTask<Any, Any, MutableLiveData<List<Plant>>>() {

        override fun doInBackground(vararg params: Any?): MutableLiveData<List<Plant>> {
            return plantDao.getAllPlantsDesc()
        }
    }

    fun loadPlantsDesc(plantDao: PlantDao): MutableLiveData<List<Plant>> {
        return getFutureEventsAsynkTask(plantDao).execute().get()
    }


}