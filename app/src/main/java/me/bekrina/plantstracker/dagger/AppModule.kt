package me.bekrina.plantstracker.dagger

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import me.bekrina.plantstracker.utility.App
import me.bekrina.plantstracker.utility.AppDatabase
import javax.inject.Singleton
/*
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApp(app: App) = app
}*/

@Module
class AppModule(val mApplication: Application){

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return mApplication
    }
}