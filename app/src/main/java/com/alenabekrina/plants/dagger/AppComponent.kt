package com.alenabekrina.plants.dagger

import android.app.Application
import com.alenabekrina.plants.notifications.AlarmReceiver
import dagger.Component
import com.alenabekrina.plants.room.AppDatabase
import com.alenabekrina.plants.view.AddPlantActivity
import com.alenabekrina.plants.view.PlantsListActivity
import com.alenabekrina.plants.view.PlantsListAdapter
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, ViewModelModule::class])
interface AppComponent {
    fun injectAlarmReceiver(alarmReceiver: AlarmReceiver)
    fun injectPlantsListAdapter(adapter: PlantsListAdapter)
    fun injectPlantsListActivity(activity: PlantsListActivity)
    fun injectAddPlantActivity(activity: AddPlantActivity)
    fun inject(application: Application)
    fun database(): AppDatabase
    fun applicationContext(): Application
}