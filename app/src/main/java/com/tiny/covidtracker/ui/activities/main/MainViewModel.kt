package com.tiny.covidtracker.ui.activities.main

import DataGlobalResponse
import DataVNResponse
import androidx.lifecycle.MutableLiveData
import com.tiny.covidtracker.SingleLiveEvent
import com.tiny.covidtracker.data.impl.HomeRepo
import com.tiny.covidtracker.data.impl.VNRepo
import com.tiny.covidtracker.model.data.AppData
import com.tiny.covidtracker.ui.bases.BaseViewModel
import com.tiny.covidtracker.ui.entites.CommonEntity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class MainViewModel(val repo: HomeRepo, val vnRepo: VNRepo) :
    BaseViewModel() {

    val globalLiveData = SingleLiveEvent<Unit>()
    var globalData: DataGlobalResponse? = null

    val totalLiveData = SingleLiveEvent<Unit>()
    var totalData: List<DataGlobalResponse>? = null

    val vnLiveData = SingleLiveEvent<Unit>()
    var vnData: DataVNResponse? = null

    val newsLiveData = MutableLiveData<MutableList<CommonEntity>>()

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

    fun getNews() {
        isLoading.postValue(true)
        val document: Document = Jsoup.connect("https://covid19.gov.vn/ban-tin-covid-19.htm").get()
        val listData: MutableList<CommonEntity> = mutableListOf()
        val elms: Elements = document.getElementsByClass("box-list-focus-item")
        for (i in elms.indices) {
            val elm_row = elms[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)
        }

        val elms2: Elements = document.getElementsByClass("box-stream-item")
        for (i in elms2.indices) {
            val elm_row = elms2[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)

        }
        val document2: Document =
            Jsoup.connect("https://covid19.gov.vn/chi-dao-chong-dich.htm").get()
        val elms3: Elements = document2.getElementsByClass("box-list-focus-item")
        for (i in elms3.indices) {
            val elm_row = elms3[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)
        }

        val elms4: Elements = document2.getElementsByClass("box-stream-item")
        for (i in elms4.indices) {
            val elm_row = elms4[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)

        }
        val document3: Document =
            Jsoup.connect("https://covid19.gov.vn/du-phong-dieu-tri.htm").get()
        val elms5: Elements = document3.getElementsByClass("box-list-focus-item")
        for (i in elms5.indices) {
            val elm_row = elms5[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)
        }

        val elms6: Elements = document3.getElementsByClass("box-stream-item")
        for (i in elms6.indices) {
            val elm_row = elms6[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)

        }
        val document4: Document =
            Jsoup.connect("https://covid19.gov.vn/vaccine-tiem-chung.htm").get()
        val elms7: Elements = document4.getElementsByClass("box-list-focus-item")
        for (i in elms7.indices) {
            val elm_row = elms7[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)
        }

        val elms8: Elements = document4.getElementsByClass("box-stream-item")
        for (i in elms8.indices) {
            val elm_row = elms8[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)

        }
        val document5: Document = Jsoup.connect("https://covid19.gov.vn/tin-tuc.htm").get()
        val elms9: Elements = document5.getElementsByClass("box-list-focus-item")
        for (i in elms9.indices) {
            val elm_row = elms9[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)
        }

        val elms10: Elements = document5.getElementsByClass("box-stream-item")
        for (i in elms10.indices) {
            val elm_row = elms10[i].getElementsByTag("a")
            val link = elm_row.first()!!.absUrl("href")
            val imgElement = elm_row.first()!!.getElementsByTag("img")
            val img = imgElement.first()!!.absUrl("src")
            val title = elm_row.first()!!.attr("title")
            val commonEntity = CommonEntity()
            commonEntity.setTitle(title)
            commonEntity.setDescript(link)
            commonEntity.setImg(img)
            listData.add(commonEntity)

        }
        isLoading.postValue(false)
        newsLiveData.postValue(listData)
    }
}
