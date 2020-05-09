package com.android.pharous.app.lava.di

import com.android.pharous.app.lava.network.ApiService
import com.android.pharous.app.lava.network.Network
import com.android.pharous.app.lava.network.RemoteDataSource
import com.android.pharous.app.lava.repositories.ILavaRepo
import com.android.pharous.app.lava.repositories.LavaRepo
import com.android.pharous.app.lava.ui.auth.login.LoginViewModel
import com.android.pharous.app.lava.ui.auth.phoneVerification.VerificationViewModel
import com.android.pharous.app.lava.ui.auth.register.RegisterViewModel
import com.android.pharous.app.lava.ui.home.HomeViewModel
import com.android.pharous.app.lava.ui.measurement.MeasurementViewModel
import com.android.pharous.app.lava.ui.point.PointViewModel
import com.android.pharous.app.lava.ui.settings.SettingsViewModel
import com.android.pharous.app.lava.ui.training.TrainingViewModel
import com.android.pharous.app.lava.ui.workout.WorkoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val network =  module {
    factory { Network.apiService }
}

private val remoteModule = module { factory { RemoteDataSource(get()) } }

private val repositoryModule = module { single<ILavaRepo> { LavaRepo(get() , get()) } }

private val viewModelModule = module {


    viewModel { LoginViewModel(get()) }
    viewModel { VerificationViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { PointViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { WorkoutViewModel(get()) }
    viewModel { TrainingViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { MeasurementViewModel(get()) }

}

fun getModules() : Array<Module>{

    return  arrayOf(
        network,
        remoteModule,
        repositoryModule,
        viewModelModule
    )
}