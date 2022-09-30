package com.example.interchallenge.domain.di

import com.example.interchallenge.domain.usecase.GetJavaRepositoriesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetJavaRepositoriesUseCase(repository = get())
    }
}

object DomainModule {
    fun load() = loadKoinModules(domainModule)
}