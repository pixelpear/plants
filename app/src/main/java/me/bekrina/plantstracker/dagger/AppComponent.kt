package me.bekrina.plantstracker.dagger

import android.app.Application
import dagger.Component
import me.bekrina.plantstracker.room.AppDatabase
import me.bekrina.plantstracker.viewmodel.PlantsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {
    fun injectViewModel(viewModel: PlantsViewModel)
    fun database(): AppDatabase
    fun applicationContext(): Application
}