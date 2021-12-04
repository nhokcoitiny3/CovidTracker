package com.tiny.covidtracker.ui.activities.main

import DataEntityResponse
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import com.tiny.covidtracker.R
import com.tiny.covidtracker.databinding.ActivitySelectCountryBinding
import com.tiny.covidtracker.model.data.AppData
import com.tiny.covidtracker.ui.adapter.Country2Adapter
import com.tiny.covidtracker.ui.bases.BaseActivity
import com.tiny.covidtracker.ui.utils.StatusBarUtil
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectCountryActivity : BaseActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SelectCountryActivity::class.java)
        }
    }

    override val viewModel: MainViewModel by viewModel()

    lateinit var binding: ActivitySelectCountryBinding

    var listDatas = mutableListOf<DataEntityResponse>()
    val adapter = Country2Adapter(mutableListOf()) {
        setResult(RESULT_OK, Intent().run {
            putExtra("flag", it)
        })
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppData.g().countryDatas.forEach {
            val data = DataEntityResponse(
                it.ID,
                it.country,
                it.countryCode,
                it.slug,
                it.newConfirmed,
                it.totalConfirmed,
                it.newDeaths,
                it.totalDeaths,
                it.newRecovered,
                it.totalRecovered,
                it.date
            )
            listDatas.add(data)

        }
        adapter.updateData(listDatas)
        initView()
        initAction()
    }

    private fun initAction() {
        binding.ivBack.setSafeOnClickListener {
            if (binding.lnSearch.visibility == View.VISIBLE) {
                binding.lnSearch.visibility = View.GONE
                binding.ivSearch.visibility = View.VISIBLE
                binding.tvTitle.visibility = View.VISIBLE
                binding.ivBack.setImageResource(R.drawable.ic_arrow_back)
            } else {
                finish()
            }
        }

        binding.ivSearch.setSafeOnClickListener {
            binding.lnSearch.visibility = View.VISIBLE
            binding.ivSearch.visibility = View.GONE
            binding.tvTitle.visibility = View.GONE
            binding.ivBack.setImageResource(R.drawable.ic_close)
        }

        binding.ivGlobal.setSafeOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
        binding.tvGlobal.setSafeOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                listDatas.clear()
                AppData.g().countryDatas.forEach {
                    val data = DataEntityResponse(
                        it.ID,
                        it.country,
                        it.countryCode,
                        it.slug,
                        it.newConfirmed,
                        it.totalConfirmed,
                        it.newDeaths,
                        it.totalDeaths,
                        it.newRecovered,
                        it.totalRecovered,
                        it.date
                    )
                    listDatas.add(data)

                }
                val temp = listDatas.filter {
                    it.countryCode.lowercase()
                        .contains(p0.toString().lowercase()) || it.country.lowercase()
                        .contains(p0.toString().lowercase())
                }
                adapter.updateData(temp)
            }

        })

    }

    fun initView() {
        StatusBarUtil.setTransparent(this)


        binding.rvCountry.adapter = adapter


    }
}