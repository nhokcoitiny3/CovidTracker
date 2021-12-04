package com.tiny.covidtracker.ui.fragment

import DataGlobalResponse
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tiny.covidtracker.R
import com.tiny.covidtracker.databinding.FragmentGlobalBinding
import com.tiny.covidtracker.model.data.Constants
import com.tiny.covidtracker.ui.activities.main.MainActivity
import com.tiny.covidtracker.ui.activities.main.MainViewModel
import com.tiny.covidtracker.ui.adapter.CountryAdapter
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GlobalFragment : Fragment() {

    val viewModel: MainViewModel by sharedViewModel()

    val adapter = CountryAdapter()

    lateinit var binding: FragmentGlobalBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGlobalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
    }

    private fun initAction() {
        binding.tvCountry.setSafeOnClickListener {
            (activity as MainActivity).selectCountry()
        }
    }

    private fun initView() {
        binding.recyclerview.adapter = adapter
    }

    fun updateDataGlobal() {

        binding.tvLastUpdate.text = getString(
            R.string.str_last_update,
            viewModel.globalData?.updated?.let {
                Companion.convertDate(
                    Constants.DATE_PATTERN_HH_MM_DDMMYYYY,
                    it
                )
            }
        )

        binding.tvCountry.text = getString(R.string.str_worldwide)
        binding.tvConfirmed.text = viewModel.globalData?.cases ?: ""
        binding.tvNewConfirm.text = viewModel.globalData?.todayCases ?: ""
        binding.tvDeath.text = viewModel.globalData?.deaths ?: ""
        binding.tvNewDeath.text = viewModel.globalData?.todayDeaths ?: ""
        binding.tvReco.text = viewModel.globalData?.todayRecovered ?: ""
        binding.tvNewReco.text = viewModel.globalData?.todayRecovered ?: ""

    }

    fun updateData(data: DataGlobalResponse?) {
        (binding.recyclerview.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            0,
            0
        )
        binding.mlRoot.transitionToStart()
        binding.tvLastUpdate.text = getString(
            R.string.str_last_update,
            viewModel.globalData?.updated?.let {
                Companion.convertDate(
                    Constants.DATE_PATTERN_HH_MM_DDMMYYYY,
                    it
                )
            }
        )

        binding.tvCountry.text = data?.country ?: ""
        binding.tvConfirmed.text = data?.cases ?: ""
        binding.tvNewConfirm.text = data?.todayCases ?: ""
        binding.tvDeath.text = data?.deaths ?: ""
        binding.tvNewDeath.text = data?.todayDeaths ?: ""
        binding.tvReco.text = data?.recovered ?: ""
        binding.tvNewReco.text = data?.todayRecovered ?: ""
    }

    fun updateListData() {
        viewModel.totalData?.let { adapter.updateData(it) }

    }

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun convertDate(toFormat: String?, strDate: Long): String {
            try {

                val simpleTo = SimpleDateFormat(toFormat)
                simpleTo.timeZone = TimeZone.getTimeZone("GMT+7");

                return simpleTo.format(Date(strDate))
            } catch (ex: ParseException) {
            }
            return ""
        }
    }

}