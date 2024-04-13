package com.example.test2

import android.app.Application
import com.example.test2.generic.di.dataModule
import com.example.test2.generic.di.domainModule
import com.example.test2.generic.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IntroPepperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin{
            androidContext(this@IntroPepperApplication)
            modules(dataModule, presentationModule, domainModule)
        }
    }
}