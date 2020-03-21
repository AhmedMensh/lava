package com.android.pharous.app.lava.di

import com.android.pharous.app.lava.network.Network
import com.android.pharous.app.lava.network.RemoteDataSource
import com.android.pharous.app.lava.repositories.ILavaRepo
import com.android.pharous.app.lava.repositories.LavaRepo
import org.koin.core.module.Module
import org.koin.dsl.module

private val network =  module {
    factory { Network.apiService }
}

private val remoteModule = module { factory { RemoteDataSource(get()) } }

private val repositoryModule = module { single<ILavaRepo> { LavaRepo(get() , get()) } }

private val viewModelModule = module {



}

fun getModules() : Array<Module>{

    return  arrayOf(
        network,
        remoteModule,
        repositoryModule,
        viewModelModule
    )
}