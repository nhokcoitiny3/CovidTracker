package com.tiny.covidtracker.data.impl

import DataTotalResponse
import com.tiny.covidtracker.data.Service

interface HomeRepo {
    suspend fun getTotalData(): DataTotalResponse?

}

class HomeRepoImpl(val service: Service) : HomeRepo {
    override suspend fun getTotalData(): DataTotalResponse? {
        return service.getTotal()
    }


}
