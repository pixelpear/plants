package me.bekrina.plantstracker.dagger

import dagger.Component
import me.bekrina.plantstracker.utility.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(app: App)
}