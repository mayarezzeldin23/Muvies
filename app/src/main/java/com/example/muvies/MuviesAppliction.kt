package com.example.muvies

import android.app.Application
import com.example.muvies.di.databaseModule
import com.example.muvies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MuviesAppliction :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MuviesAppliction)
            modules(listOf(databaseModule, viewModelModule))
        }
    }
}