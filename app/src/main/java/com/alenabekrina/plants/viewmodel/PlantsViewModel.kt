package com.alenabekrina.plants.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.alenabekrina.plants.model.Plant
import com.alenabekrina.plants.repository.Repository
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class PlantsViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private lateinit var plants: LiveData<List<Plant>>

    fun getPlantsDesc(): LiveData<List<Plant>> {
        if (!::plants.isInitialized) {
            plants = repository.getAllPlantsNameDesc()
        }
        return plants
    }

    fun createAndSavePlant(name: String, type: String, wateringInterval: String, daysSinceLastWatering: String) {
        val plant = Plant()
        plant.name = name
        plant.type = type
        plant.wateringInterval = wateringInterval.toInt()

        val today = OffsetDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        plant.lastWateringDate = today.plusDays(daysSinceLastWatering.toLong())

        repository.insertPlant(plant)
    }

}
