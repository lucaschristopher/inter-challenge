package com.example.interchallenge.presentation.di

import com.example.interchallenge.presentation.viewmodel.AppViewModel
import com.example.interchallenge.presentation.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AppViewModel(appUseCase = get()) }
    viewModel { DetailViewModel(appUseCase = get()) }
}

object PresentationModule {
    fun load() = loadKoinModules(viewModelModule)
}