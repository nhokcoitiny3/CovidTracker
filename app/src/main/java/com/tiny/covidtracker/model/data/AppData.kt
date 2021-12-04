package com.tiny.covidtracker.model.data

import DataGlobalResponse

class AppData {
    companion object {
        private var instance: AppData? = null

        fun g(): AppData {
            if (instance == null) {
                instance = AppData()
            }
            return instance!!
        }
    }

    var countryDatas: MutableList<DataGlobalResponse> = mutableListOf()

}
