package me.bekrina.plantstracker.dagger

import dagger.Module
import dagger.Provides
import me.bekrina.plantstracker.utility.App
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app
}