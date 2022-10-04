package com.example.interchallenge.domain.di

import com.example.interchallenge.domain.usecase.AppUseCase
import com.example.interchallenge.domain.usecase.GetJavaRepositoriesUseCase
import com.example.interchallenge.domain.usecase.GetPullRequestsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val domainModule = module {
    factory { AppUseCase(getJavaRepositoriesUseCase = get(), getPullRequestsUseCase = get()) }
    factory { GetJavaRepositoriesUseCase(repository = get()) }
    factory { GetPullRequestsUseCase(repository = get()) }
}

object DomainModule {
    fun load() = loadKoinModules(domainModule)
}