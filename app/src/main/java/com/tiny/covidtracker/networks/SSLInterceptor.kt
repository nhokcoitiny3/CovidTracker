package com.tiny.covidtracker.networks

import com.tiny.covidtracker.constants.DatasourceProperties
import okhttp3.*
import org.json.JSONObject
import java.net.UnknownHostException


class SSLInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response?
        try {
            val request = Request.Builder().cacheControl(CacheControl.FORCE_NETWORK)
                .url(chain.request().url)
                .get()
                .build()
            response = chain.proceed(request)
            if (response.isSuccessful) {
                response =
                    response.newBuilder().code(200)
                        .body(response.body).build()
            }
            return response
        } catch (unknown: UnknownHostException) {
            throw unknown
            return response!!
        } catch (e: java.lang.Exception) {

            return Response.Builder()
                .code(503)
                .message("IOException")
                .addHeader("code", "IO")
                .protocol(Protocol.HTTP_1_1)
                .request(
                    Request.Builder()
                        .url(DatasourceProperties.SERVER_URL)
                        .get()
                        .build()
                )
                .build()
        }
    }

    private fun getMid(strOldBody: String?): String {
        try {
            val json = JSONObject(strOldBody)
        } catch (e: java.lang.Exception) {

        }
        return ""
    }

}

