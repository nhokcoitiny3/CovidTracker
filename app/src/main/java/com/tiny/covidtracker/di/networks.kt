package com.tiny.covidtracker.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tiny.covidtracker.data.Service
import com.vnpay.base.constants.DatasourceProperties
import com.tiny.covidtracker.networks.remote.MainRemote
import com.tiny.covidtracker.networks.SSLInterceptor
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networks = module {
    single { createOkHttpCache(get()) }
    single { createGson() }
    single { createOkHttpClient(get()) }
    factory<OkHttpClient>(named("newOkHttp"))
    {
        newBuildOkHttp(
            get(),
            DatasourceProperties.TIMEOUT_CONNECT,
            DatasourceProperties.TIMEOUT_READ,
        )
    }
    single { createMediaType() }
    single { createService(get(named("newOkHttp"))) }


}

fun createOkHttpClient(
    cache: Cache
): OkHttpClient {

    return OkHttpClient.Builder()
        .cache(cache)
        .build()
}

fun createOkHttpCache(context: Context): Cache {
    return Cache(context.cacheDir, Long.MAX_VALUE)
}

fun newBuildOkHttp(
    client: OkHttpClient,
    timeoutConnect: Long,
    timeoutReader: Long
): OkHttpClient {
    val log = HttpLoggingInterceptor()
    log.setLevel(HttpLoggingInterceptor.Level.BODY)
    log.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    return client.newBuilder()
        .connectTimeout(timeoutConnect, TimeUnit.SECONDS)
        .addInterceptor(SSLInterceptor())
        .addInterceptor(log)
        .readTimeout(timeoutReader, TimeUnit.SECONDS).build()
}

fun createGson(): Gson {
    return GsonBuilder().create()
}

fun createMediaType(): MediaType? {
    return "application/octet-stream".toMediaTypeOrNull()
}

fun createService(client: OkHttpClient): Service {
    return MainRemote(client).getService()
}



