package com.tiny.covidtracker.di

import com.vnpay.base.di.AndroidResourceProvider
import com.vnpay.base.di.ResourceProvider
import com.tiny.covidtracker.data.impl.HomeRepo
import com.tiny.covidtracker.data.impl.HomeRepoImpl
import com.tiny.covidtracker.data.impl.VNRepo
import com.tiny.covidtracker.data.impl.VNRepoImpl
import com.tiny.covidtracker.ui.activities.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val models = module {

    viewModel {
        MainViewModel(get(),get())
    }
}

val impls = module {
    single<HomeRepo>  {
        HomeRepoImpl(
            get()
        )
    }
    single<VNRepo>  {
        VNRepoImpl(
            get()
        )
    }
}
val utilities = module{
    single<ResourceProvider> { AndroidResourceProvider(get()) }
}

val modules = listOf(utilities, networks, models, dialogs, impls)