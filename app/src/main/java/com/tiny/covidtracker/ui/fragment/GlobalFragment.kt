package com.tiny.covidtracker.ui.fragment

import DataEntityResponse
import DataTotalResponse
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

    fun updateData(data: DataEntityResponse?) {
        (binding.recyclerview.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            0,
            0
        )
        binding.mlRoot.transitionToStart()
        if (data == null) {
            binding.tvLastUpdate.text = getString(
                R.string.str_last_update,
                convertDate(
                    Constants.DATE_PATTERN_YYYY_MM_DD_HH_MM_SS,
                    Constants.DATE_PATTERN_HH_MM_DDMMYYYY,
                    viewModel.totalData?.global?.date ?: ""
                )
            )

            binding.tvCountry.text = getString(R.string.str_worldwide)

            binding.tvConfirmed.text = viewModel.totalData?.global?.totalConfirmed ?: ""
            binding.tvNewConfirm.text = viewModel.totalData?.global?.newConfirmed ?: ""
            binding.tvDeath.text = viewModel.totalData?.global?.totalDeaths ?: ""
            binding.tvNewDeath.text = viewModel.totalData?.global?.newDeaths ?: ""
            binding.tvReco.text = viewModel.totalData?.global?.totalRecovered ?: ""
            binding.tvNewReco.text = viewModel.totalData?.global?.newRecovered ?: ""
        } else {
            binding.tvLastUpdate.text = getString(
                R.string.str_last_update,
                convertDate(
                    Constants.DATE_PATTERN_YYYY_MM_DD_HH_MM_SS,
                    Constants.DATE_PATTERN_HH_MM_DDMMYYYY,
                    data.date
                )
            )

            binding.tvCountry.text = data.country
            binding.tvConfirmed.text = data.totalConfirmed
            binding.tvNewConfirm.text = data.newConfirmed
            binding.tvDeath.text = data.totalDeaths
            binding.tvNewDeath.text = data.newDeaths
            binding.tvReco.text = data.totalRecovered
            binding.tvNewReco.text = data.newRecovered
        }
        viewModel.totalData?.countries?.let { adapter.updateData(it) }
    }

    fun convertDate(fromFormat: String?, toFormat: String?, strDate: String): String {
        try {
            val simpleDateFormat = SimpleDateFormat(fromFormat)
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC");

            val simpleTo = SimpleDateFormat(toFormat)
            simpleTo.timeZone = TimeZone.getTimeZone("GMT+7");

            return simpleTo.format(simpleDateFormat.parse(strDate))
        } catch (ex: ParseException) {
        }
        return strDate
    }

}