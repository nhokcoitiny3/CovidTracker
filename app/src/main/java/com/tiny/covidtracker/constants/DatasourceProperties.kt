package com.vnpay.base.constants


object DatasourceProperties {
    fun getUrl(): String {
        return SERVER_URL
    }
    fun getUrlVn(): String {
        return SERVER_URL_VN
    }


    const val SERVER_URL = "https://corona.lmao.ninja"
    const val SERVER_URL_VN = "https://static.pipezero.com"

    const val TIMEOUT_CONNECT: Long = 30
    const val TIMEOUT_READ: Long = 90

}