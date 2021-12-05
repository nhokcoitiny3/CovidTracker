package com.tiny.covidtracker.networks.remote

import com.google.gson.GsonBuilder
import com.tiny.covidtracker.data.ServiceVn
import com.tiny.covidtracker.constants.DatasourceProperties
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UtitlityRemote(client: OkHttpClient){
    private val service: ServiceVn

    init {
        val gson = GsonBuilder()
            .setLenient()
            .disableHtmlEscaping()
            .create()
        service = Retrofit.Builder()
            .baseUrl(DatasourceProperties.getUrlVn())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build().create(
                ServiceVn::class.java
            )
    }

    fun getService(): ServiceVn {
        return service
    }
}