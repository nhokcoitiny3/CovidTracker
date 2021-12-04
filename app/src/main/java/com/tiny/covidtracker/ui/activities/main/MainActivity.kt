package com.tiny.covidtracker.ui.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tiny.covidtracker.R
import com.tiny.covidtracker.databinding.ActivityMainBinding
import com.tiny.covidtracker.model.data.AppData
import com.tiny.covidtracker.ui.bases.BaseActivity
import com.tiny.covidtracker.ui.fragment.GlobalFragment
import com.tiny.covidtracker.ui.utils.StatusBarUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    lateinit var navController: NavController

    private var resultActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment)
                    ?.childFragmentManager?.fragments?.forEach {
                        if (it is GlobalFragment) {
                            it.updateData(viewModel.totalData?.countries?.find {
                                it.countryCode == result.data?.getStringExtra("flag")
                            })
                        }
                    }
            }
        }

    override val viewModel: MainViewModel by viewModel()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        initView()
        viewModel.getDataTotals()
    }

    private fun initView() {
        StatusBarUtil.setTransparent(this)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        navController = host.navController

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeDest -> {
                    viewModel.getDataTotals()
                }
            }

        }
        setupBottomNavMenu(navController = navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        binding.bottomNavView.setupWithNavController(navController)
    }

    private fun initObserver() {
        viewModel.apply {
            totalLiveData.observe(this@MainActivity, {
                supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment)
                    ?.childFragmentManager?.fragments?.forEach {
                        if (it is GlobalFragment) {
                            it.updateData(null)
                        }
                    }
            })
        }
    }

    fun selectCountry() {
        resultActivity.launch(SelectCountryActivity.getIntent(this))
    }

    override fun onBackPressed() {
        supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment)
            ?.childFragmentManager?.fragments?.forEach {
                if (it is GlobalFragment) {
                    finish()
                } else {
                    super.onBackPressed()
                    viewModel.getDataTotals()
                }
            }
    }
}
