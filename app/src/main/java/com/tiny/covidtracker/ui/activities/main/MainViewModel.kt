package com.tiny.covidtracker.ui.activities.main

import DataEntityResponse
import DataTotalResponse
import androidx.lifecycle.MutableLiveData
import com.tiny.covidtracker.SingleLiveEvent
import com.tiny.covidtracker.data.impl.HomeRepo
import com.tiny.covidtracker.model.data.AppData
import com.tiny.covidtracker.ui.bases.BaseViewModel

class MainViewModel(val repo: HomeRepo) :
    BaseViewModel() {

    val totalLiveData = SingleLiveEvent<Unit>()
    var totalData : DataTotalResponse? = null

    fun getDataTotals() = launch {
        val response = repo.getTotalData()
        if (response != null) {
            totalData = response
            AppData.g().countryDatas = response.countries as MutableList<DataEntityResponse>
        }
        totalLiveData.call()
    }
}
