package me.bekrina.plantstracker.utility

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import me.bekrina.plantstracker.dagger.AppComponent
import me.bekrina.plantstracker.dagger.AppModule
import me.bekrina.plantstracker.dagger.DaggerAppComponent
import javax.inject.Singleton


/*class App: Application() {
    val DATABASE_NAME = "Local Database"
    lateinit var database: AppDatabase
    override fun onCreate() {
        super.onCreate()

    }


}*/
class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}