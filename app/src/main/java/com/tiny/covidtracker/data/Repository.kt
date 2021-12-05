package com.tiny.covidtracker.data

import DataGlobalResponse
import DataVNResponse
import com.tiny.covidtracker.constants.Messages.END_POINT_GLOBAL
import com.tiny.covidtracker.constants.Messages.END_POINT_TOTAL
import com.tiny.covidtracker.constants.Messages.END_POINT_VIETNAM
import retrofit2.http.GET

interface Service {
    @GET(END_POINT_GLOBAL)
    suspend fun getGlobal(): DataGlobalResponse?

    @GET(END_POINT_TOTAL)
    suspend fun getTotal(): List<DataGlobalResponse>?

}

interface ServiceVn {
    @GET(END_POINT_VIETNAM)
    suspend fun getVietNam(): DataVNResponse?

}
