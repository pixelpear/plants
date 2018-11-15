package com.alenabekrina.plants.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.alenabekrina.plants.model.Plant
import com.alenabekrina.plants.repository.Repository
import javax.inject.Inject

class PlantsViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private lateinit var plants: LiveData<List<Plant>>

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
