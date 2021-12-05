package com.tiny.covidtracker.ui.activities.main

import LocationEntityResponse
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tiny.covidtracker.constants.Tags
import com.tiny.covidtracker.databinding.ActivityProvinceBinding
import com.tiny.covidtracker.di.createGson
import com.tiny.covidtracker.ui.bases.BaseActivity
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProvinceActivity : BaseActivity() {
    override val viewModel: MainViewModel by viewModel()

    private val data: LocationEntityResponse by lazy {
        createGson().fromJson(
            intent.getStringExtra(Tags.KEY_INTENT_DATA),
            LocationEntityResponse::class.java
        )
    }

    companion object {
        fun getIntent(context: Context, data: LocationEntityResponse): Intent {
            return Intent(context, ProvinceActivity::class.java).run {
                putExtra(Tags.KEY_INTENT_DATA, createGson().toJson(data))
            }
        }
    }

    lateinit var binding: ActivityProvinceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProvinceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initAction()
    }

    fun initView() {
        binding.tvTitle.text = data.name
        binding.tvCasesToday.text = data.casesToday
        binding.tvCases.text = data.cases
        binding.tvTreating.text = data.treating
        binding.tvRecovered.text = data.recovered
        binding.tvDeath.text = data.death
    }

    fun initAction() {
        binding.ivBack.setSafeOnClickListener {
            finish()
        }
    }
}