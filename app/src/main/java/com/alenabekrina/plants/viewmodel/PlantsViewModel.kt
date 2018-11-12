package com.alenabekrina.plants.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.alenabekrina.plants.dagger.AppModule
import com.alenabekrina.plants.dagger.DaggerAppComponent
import com.alenabekrina.plants.dagger.DatabaseModule
import com.alenabekrina.plants.model.Plant
import com.alenabekrina.plants.repository.Repository
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
