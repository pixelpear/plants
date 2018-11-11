package me.bekrina.plantstracker.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
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