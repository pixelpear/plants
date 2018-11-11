package me.bekrina.plantstracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import me.bekrina.plantstracker.dagger.AppModule
import me.bekrina.plantstracker.dagger.DaggerAppComponent
import me.bekrina.plantstracker.dagger.DatabaseModule
import me.bekrina.plantstracker.model.Plant
import me.bekrina.plantstracker.room.PlantDao
import me.bekrina.plantstracker.room.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantsViewModel(val app : Application) : AndroidViewModel(app) {
    @Inject
    lateinit var database: AppDatabase
    private lateinit var plants: LiveData<List<Plant>>
    private lateinit var plantDao: PlantDao

    init {
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .databaseModule(DatabaseModule())
            .build()
            .injectViewModel(this)
        plantDao = database.plantDao()
        PopulateDbAsync(database).execute()
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

    fun insertPlant(plant: Plant) {
        plantDao.insertPlant(plant)
    }


    private class PopulateDbAsync internal constructor(db: AppDatabase) : AsyncTask<Void, Void, Void>() {

        private val dao: PlantDao

        init {
            dao = db.plantDao()
        }

        override fun doInBackground(vararg params: Void): Void? {

            var plant = Plant()
            plant.name = "Kate"
            plant.type = "Palm"
            plant.wateringPeriod = 2
            dao.insertPlant(plant)

            return null
        }
    }
}
