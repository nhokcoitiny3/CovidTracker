package com.tiny.covidtracker.data.impl

import DataGlobalResponse
import DataVNResponse
import com.tiny.covidtracker.data.Service
import com.tiny.covidtracker.data.ServiceVn

interface VNRepo {
    suspend fun getVietNamData(): DataVNResponse?

}

class VNRepoImpl(val service: ServiceVn) : VNRepo {

    override suspend fun getVietNamData(): DataVNResponse? {
        return service.getVietNam()
    }


}
