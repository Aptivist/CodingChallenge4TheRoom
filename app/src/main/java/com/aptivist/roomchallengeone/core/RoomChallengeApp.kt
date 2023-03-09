package com.aptivist.roomchallengeone.core

import android.app.Application
import com.aptivist.roomchallengeone.di.networkModule
import com.aptivist.roomchallengeone.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RoomChallengeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RoomChallengeApp)
            androidFileProperties("secrets.properties")
            modules(networkModule, repositoryModule)
        }
    }
}