package com.vnpay.base.constants


object DatasourceProperties {
    fun getUrl(): String {
        return SERVER_URL
    }


    const val SERVER_URL = "https://api.covid19api.com/"

    const val TIMEOUT_CONNECT: Long = 30
    const val TIMEOUT_READ: Long = 90

}