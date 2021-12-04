package com.tiny.covidtracker.di

import com.tiny.covidtracker.ui.dialogs.LoadingDialog
import com.vnpay.base.di.Common
import org.koin.dsl.module

val dialogs = module {
    factory { createLoadingDialog() }
}

fun createLoadingDialog(): LoadingDialog {
    return LoadingDialog(Common.currentActivity)
}
