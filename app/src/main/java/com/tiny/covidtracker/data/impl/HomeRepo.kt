package com.tiny.covidtracker.data.impl

import DataGlobalResponse
import DataVNResponse
import com.tiny.covidtracker.data.Service

interface HomeRepo {
    suspend fun getTotalData(): List<DataGlobalResponse>?
    suspend fun getGlobalData(): DataGlobalResponse?
}

class HomeRepoImpl(val service: Service) : HomeRepo {
    override suspend fun getTotalData(): List<DataGlobalResponse>? {
        return service.getTotal()
    }

    override suspend fun getGlobalData(): DataGlobalResponse? {
        return service.getGlobal()
    }


}
