package com.alenabekrina.plants.dagger

import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatActivity
import com.alenabekrina.plants.notifications.AlarmReceiver
import com.alenabekrina.plants.notifications.PlantsNotificationManager
import dagger.Component
import com.alenabekrina.plants.room.AppDatabase
import com.alenabekrina.plants.view.AddPlantActivity
import com.alenabekrina.plants.view.PlantsListActivity
import com.alenabekrina.plants.view.PlantsListAdapter
import com.alenabekrina.plants.viewmodel.PlantsViewModel
import com.alenabekrina.plants.viewmodel.ViewModelFactory
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