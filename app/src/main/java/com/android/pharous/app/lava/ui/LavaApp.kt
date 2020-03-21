package com.android.pharous.app.lava.ui

import android.app.Application
import android.provider.SyncStateContract
import com.android.pharous.app.lava.BuildConfig
import com.android.pharous.app.lava.common.Constants
import com.android.pharous.app.lava.di.getModules
import com.android.pharous.app.lava.network.Network
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LavaApp : Application() {

    override fun onCreate() {
        super.onCreate()



        Network.init(Constants.BASE_URL, BuildConfig.DEBUG)

        startKoin {
            this@LavaApp
            androidContext(this@LavaApp)
            modules(*getModules())
        }
    }
}