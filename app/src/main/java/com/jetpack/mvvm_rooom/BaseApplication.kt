package com.jetpack.mvvm_rooom

import android.app.Application
import com.jetpack.mvvm_rooom.di.koin_modules.databaseModule
import com.jetpack.mvvm_rooom.di.koin_modules.mainActivityRepositoryModule
import com.jetpack.mvvm_rooom.di.koin_modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //start KOIN
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(databaseModule,mainActivityRepositoryModule,viewModelModule ))
        }
    }
}