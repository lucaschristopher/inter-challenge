package com.example.interchallenge.data.di

import com.example.interchallenge.BuildConfig
import com.example.interchallenge.core.constants.DEFAULT_DISPATCHER
import com.example.interchallenge.core.constants.DEFAULT_SCOPE
import com.example.interchallenge.core.constants.IO_DISPATCHER
import com.example.interchallenge.core.constants.MAIN_DISPATCHER
import com.example.interchallenge.data.datasource.remote.RemoteDataSource
import com.example.interchallenge.data.datasource.remote.RemoteDataSourceImpl
import com.example.interchallenge.data.provider.ApiFactory
import com.example.interchallenge.data.provider.OkHttpClientFactory
import com.example.interchallenge.data.provider.RetrofitFactory
import com.example.interchallenge.data.repository.AppRepository
import com.example.interchallenge.data.repository.AppRepositoryImpl
import com.example.interchallenge.data.service.AppService
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    factory { ApiFactory.build(retrofit = get(), apiClass = AppService::class.java) }
}

val networkModule = module {
    single {
        OkHttpClientFactory.build()
    }

    single<Converter.Factory> {
        GsonConverterFactory.create(GsonBuilder().create())
    }

    single {
        RetrofitFactory.build(url = BuildConfig.BASE_URL, client = get(), factory = get())
    }
}

val repositoryModule = module {
    factory<AppRepository> {
        AppRepositoryImpl(
            remoteDataSource = get(),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }
}

val dataSourceModule = module {
    factory<RemoteDataSource> {
        RemoteDataSourceImpl(
            service = get()
        )
    }
}

val dispatcherModule = module {
    factory(named(DEFAULT_DISPATCHER)) { Dispatchers.Default }
    factory(named(IO_DISPATCHER)) { Dispatchers.IO }
    factory(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    factory(named(DEFAULT_SCOPE)) { CoroutineScope(Dispatchers.Default) }
}

object DataModule {
    fun load() = loadKoinModules(
        networkModule + apiModule + repositoryModule + dataSourceModule + dispatcherModule
    )
}