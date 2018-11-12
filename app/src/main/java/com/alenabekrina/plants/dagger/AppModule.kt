package com.alenabekrina.plants.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val mApplication: Application){

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return mApplication
    }
}