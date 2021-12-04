package com.tiny.covidtracker.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.tiny.covidtracker.R
import com.tiny.covidtracker.databinding.FragmentVietnamBinding
import com.tiny.covidtracker.ui.activities.main.MainViewModel
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.tiny.covidtracker.model.data.Constants.DATE_PATTERN_MMDD
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet





class VietNamFragment : Fragment() {
    val viewModel: MainViewModel by sharedViewModel()

    lateinit var binding: FragmentVietnamBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentVietnamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
    }

    private fun initAction() {

        binding.tvSumary.setSafeOnClickListener {
            binding.tvSumary.isSelected = true
            binding.tvSumary.setBackgroundResource(R.drawable.bg_selected)
            binding.tvSumary.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black
                )
            )

            binding.tvToday.isSelected = false
            binding.tvToday.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.tvToday.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black_60
                )
            )

            binding.tv7Day.isSelected = false
            binding.tv7Day.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.tv7Day.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black_60
                )
            )
            updateDataTotal()
            binding.tvAvgCaces.visibility = View.GONE
            binding.tvAvgRecovered.visibility = View.GONE
            binding.tvAvgDeaths.visibility = View.GONE
            binding.lineChart.visibility = View.GONE
            binding.lnSummary.visibility = View.VISIBLE
            binding.lnSummary2.visibility = View.VISIBLE
        }
        binding.tvToday.setSafeOnClickListener {
            binding.tvToday.isSelected = true
            binding.tvToday.setBackgroundResource(R.drawable.bg_selected)
            binding.tvToday.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black
                )
            )

            binding.tvSumary.isSelected = false
            binding.tvSumary.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.tvSumary.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black_60
                )
            )

            binding.tv7Day.isSelected = false
            binding.tv7Day.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.tv7Day.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black_60
                )
            )
            updateDataToday()

            binding.lineChart.visibility = View.GONE
            binding.tvAvgCaces.visibility = View.GONE
            binding.tvAvgRecovered.visibility = View.GONE
            binding.tvAvgDeaths.visibility = View.GONE
            binding.lnSummary.visibility = View.VISIBLE
            binding.lnSummary2.visibility = View.VISIBLE
        }
        binding.tv7Day.setSafeOnClickListener {
            binding.tv7Day.isSelected = true
            binding.tv7Day.setBackgroundResource(R.drawable.bg_selected)
            binding.tv7Day.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black
                )
            )

            binding.tvToday.isSelected = false
            binding.tvToday.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.tvToday.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black_60
                )
            )

            binding.tvSumary.isSelected = false
            binding.tvSumary.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.tvSumary.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_text_black_60
                )
            )

            binding.lineChart.visibility = View.VISIBLE
            binding.tvAvgCaces.visibility = View.VISIBLE
            binding.tvAvgRecovered.visibility = View.VISIBLE
            binding.tvAvgDeaths.visibility = View.VISIBLE
            binding.lnSummary.visibility = View.GONE
            binding.lnSummary2.visibility = View.GONE
            settingChart()
        }
    }

    private fun initView() {

    }

    fun updateDataTotal() {
        binding.tvCases.text = viewModel.vnData?.total?.internal?.cases ?: ""
        binding.tvTreating.text = viewModel.vnData?.total?.internal?.treating ?: ""
        binding.tvRecovered.text = viewModel.vnData?.total?.internal?.recovered ?: ""
        binding.tvDeaths.text = viewModel.vnData?.total?.internal?.death ?: ""
    }

    fun updateDataToday() {
        binding.tvCases.text = viewModel.vnData?.today?.internal?.cases ?: ""
        binding.tvTreating.text = viewModel.vnData?.today?.internal?.treating ?: ""
        binding.tvRecovered.text = viewModel.vnData?.today?.internal?.recovered ?: ""
        binding.tvDeaths.text = viewModel.vnData?.today?.internal?.death ?: ""
    }

    fun settingChart() {
        if (viewModel.vnData?.overview != null) {
            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            val entriesCases: MutableList<Entry> = mutableListOf()
            val entriesRecovered: MutableList<Entry> = mutableListOf()
            val entriesDeath: MutableList<Entry> = mutableListOf()
            for (data in viewModel.vnData?.overview!!) {
                entriesCases.add(
                    Entry(
                        convertDate(DATE_PATTERN_MMDD,data.date?:"0").toFloat(),
                        data.cases.toString().toFloat()
                    )
                )
                entriesRecovered.add(
                    Entry(
                        convertDate(DATE_PATTERN_MMDD,data.date?:"0").toFloat(),
                        data.recovered.toString().toFloat()
                    )
                )
                entriesDeath.add(
                    Entry(
                        convertDate(DATE_PATTERN_MMDD,data.date?:"0").toFloat(),
                        data.death.toString().toFloat()
                    )
                )
            }
            val dataSetCase = LineDataSet(entriesCases, requireContext().getString(R.string.str_cases))
            dataSetCase.color = ContextCompat.getColor(requireContext(),R.color.color_blue)
            val dataSetRecovered = LineDataSet(entriesRecovered,  requireContext().getString(R.string.str_recovered))
            dataSetRecovered.color = ContextCompat.getColor(requireContext(),R.color.color_green)
            val dataSetDeath = LineDataSet(entriesDeath,  requireContext().getString(R.string.str_deaths))
            dataSetDeath.color = ContextCompat.getColor(requireContext(),R.color.color_red)
            dataSets.add(dataSetCase);
            dataSets.add(dataSetRecovered);
            dataSets.add(dataSetDeath);

            val lineData = LineData(dataSets)
            binding.lineChart.data = lineData
            binding.lineChart.xAxis.valueFormatter = LineChartXAxisValueFormatter()
            binding.lineChart.setDrawGridBackground(false)
            binding.lineChart.setBackgroundColor(Color.TRANSPARENT)
            val description = Description()
            description.text =""
            binding.lineChart.setDescription(description)
            binding.lineChart.invalidate()

            binding.tvAvgCaces.text = getString(R.string.str_avg_cases,
                viewModel.vnData?.overview!![0].avgCases7day)
            binding.tvAvgRecovered.text = getString(R.string.str_avg_reco,
                viewModel.vnData?.overview!![0].avgRecovered7day)
            binding.tvAvgDeaths.text = getString(R.string.str_avg_death,
                viewModel.vnData?.overview!![0].avgDeath7day)
        }

    }
    class LineChartXAxisValueFormatter : IndexAxisValueFormatter() {
        override fun getFormattedValue(value: Float, axis: AxisBase?): String? {

            val emissionsMilliSince1970Time = value.toLong()

            // Show time in local version
            val timeMilliseconds = Date(emissionsMilliSince1970Time)
            val dateTimeFormat = SimpleDateFormat("dd-MM")
            return dateTimeFormat.format(timeMilliseconds)
        }
    }

    private fun convertDate(fromFormat: String?, strDate: String): Long {
        try {

            val simpleTo = SimpleDateFormat(fromFormat)

            return simpleTo.parse(strDate).time
        } catch (ex: ParseException) {
        }
        return 0
    }

}