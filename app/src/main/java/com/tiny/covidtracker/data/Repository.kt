package com.tiny.covidtracker.data

import DataTotalResponse
import com.vnpay.base.constants.Messages
import com.vnpay.base.constants.Messages.END_POINT_TOTAL
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {
    @Throws(Exception::class)
    @GET(END_POINT_TOTAL)
    suspend fun getTotal(): DataTotalResponse?

}
