package com.alenabekrina.plants

import android.app.Application
import com.alenabekrina.plants.dagger.AppComponent
import com.alenabekrina.plants.dagger.AppModule
import com.alenabekrina.plants.dagger.DaggerAppComponent
import com.alenabekrina.plants.dagger.DatabaseModule
import com.jakewharton.threetenabp.AndroidThreeTen

class App: Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        component.inject(this)
    }
}