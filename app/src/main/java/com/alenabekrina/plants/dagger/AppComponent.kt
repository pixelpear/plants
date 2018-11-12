package com.alenabekrina.plants.dagger

import android.app.Application
import dagger.Component
import com.alenabekrina.plants.room.AppDatabase
import com.alenabekrina.plants.viewmodel.PlantsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {
    fun injectViewModel(viewModel: PlantsViewModel)
    fun database(): AppDatabase
    fun applicationContext(): Application
}