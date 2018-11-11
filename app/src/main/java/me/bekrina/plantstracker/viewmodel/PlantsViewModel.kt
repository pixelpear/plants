package me.bekrina.plantstracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.bekrina.plantstracker.dagger.AppModule
import me.bekrina.plantstracker.dagger.DaggerAppComponent
import me.bekrina.plantstracker.dagger.DatabaseModule
import me.bekrina.plantstracker.model.Plant
import me.bekrina.plantstracker.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantsViewModel(val app: Application): AndroidViewModel(app) {
    //TODO: make viewmodelfactory and module for it
    @Inject
    lateinit var repository: Repository
    private lateinit var plants: LiveData<List<Plant>>

    init {
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .databaseModule(DatabaseModule())
            .build()
            .injectViewModel(this)
    }

    fun getPlantsDesc(): LiveData<List<Plant>> {
        if (!::plants.isInitialized) {
            plants = repository.getAllPlantsNameDesc()
        }
        return plants
    }

    fun insertPlant(plant: Plant) {
        repository.insertPlant(plant)
    }

}
