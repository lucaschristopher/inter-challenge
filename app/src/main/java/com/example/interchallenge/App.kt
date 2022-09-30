package com.example.interchallenge

import android.app.Application
import com.example.interchallenge.data.di.DataModule
import com.example.interchallenge.data.provider.ContextProvider
import com.example.interchallenge.domain.di.DomainModule
import com.example.interchallenge.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupProvider()
        setupKoin()
    }

    private fun setupProvider() {
        ContextProvider.initialContext(this.applicationContext)
    }

    private fun setupKoin() {
        initKoin()
        loadModules()
    }

    private fun initKoin() {
        startKoin { androidContext(this@App) }
    }

    private fun loadModules() {
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}