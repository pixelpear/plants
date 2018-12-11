package com.alenabekrina.plants.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.alenabekrina.plants.viewmodel.PlantsViewModel
import com.alenabekrina.plants.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlantsViewModel::class)
    abstract fun bindPlantsViewModel(plantsViewModel: PlantsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}