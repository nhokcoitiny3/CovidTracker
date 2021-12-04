package com.tiny.covidtracker.main

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CovidTrackerApp : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CovidTrackerApp)
            modules(com.tiny.covidtracker.di.modules)

        }
    }

    companion object {
        private var instance: CovidTrackerApp? = null

        fun getInstanceApp(): CovidTrackerApp {
            return instance as CovidTrackerApp
        }
    }
}
