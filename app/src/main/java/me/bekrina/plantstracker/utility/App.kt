package me.bekrina.plantstracker.utility

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import me.bekrina.plantstracker.dagger.AppComponent
import me.bekrina.plantstracker.dagger.AppModule
import me.bekrina.plantstracker.dagger.DaggerAppComponent
import javax.inject.Inject
import javax.inject.Singleton

class App : Application() {
    lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        /*component = DaggerAppComponent.builder()
            .appModule(AppModule((this)))
            .build()*/
    }
}