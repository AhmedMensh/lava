package com.android.pharous.app.lava.repositories

import android.content.Context
import com.android.pharous.app.lava.network.RemoteDataSource

class LavaRepo(private val remoteDataSource: RemoteDataSource , private val context: Context) : ILavaRepo {
}