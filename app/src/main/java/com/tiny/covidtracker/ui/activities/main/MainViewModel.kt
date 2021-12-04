package com.tiny.covidtracker.ui.activities.main

import DataGlobalResponse
import DataVNResponse
import com.tiny.covidtracker.SingleLiveEvent
import com.tiny.covidtracker.data.impl.HomeRepo
import com.tiny.covidtracker.data.impl.VNRepo
import com.tiny.covidtracker.model.data.AppData
import com.tiny.covidtracker.ui.bases.BaseViewModel

class MainViewModel(val repo: HomeRepo,val vnRepo: VNRepo) :
    BaseViewModel() {

    val globalLiveData = SingleLiveEvent<Unit>()
    var globalData : DataGlobalResponse? = null

    val totalLiveData = SingleLiveEvent<Unit>()
    var totalData : List<DataGlobalResponse>? = null

    val vnLiveData = SingleLiveEvent<Unit>()
    var vnData : DataVNResponse? = null

    fun getDataGlobal() = launch {
        val response = repo.getGlobalData()
        if (response != null) {
            globalData = response
        }
        globalLiveData.call()
    }
    fun getDataTotals() = launch {
        val response = repo.getTotalData()
        if (response != null) {
            totalData = response
            AppData.g().countryDatas = response as MutableList<DataGlobalResponse>
        }
        totalLiveData.call()
    }
    fun getDataVietNam() = launch {
        val response = vnRepo.getVietNamData()
        if (response != null) {
            vnData = response
        }
        vnLiveData.call()
    }
}
