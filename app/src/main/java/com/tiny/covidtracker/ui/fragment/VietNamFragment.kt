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
import com.github.mikephil.charting.data.*
import com.tiny.covidtracker.R
import com.tiny.covidtracker.databinding.FragmentVietnamBinding
import com.tiny.covidtracker.ui.activities.main.MainViewModel
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.tiny.covidtracker.model.data.Constants.DATE_PATTERN_MMDD
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.tiny.covidtracker.ui.adapter.ProvinceAdapter
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.charts.BarLineChartBase

import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.tiny.covidtracker.ui.activities.main.ProvinceActivity


class VietNamFragment : Fragment() {
    val viewModel: MainViewModel by sharedViewModel()

    lateinit var binding: FragmentVietnamBinding


    val adapter = ProvinceAdapter() {
        startActivity(ProvinceActivity.getIntent(requireContext(), it))
    }

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
        binding.recyclerview.adapter = adapter
    }

    fun updateDataTotal() {
        binding.tvCases.text = viewModel.vnData?.total?.internal?.cases ?: ""
        binding.tvTreating.text = viewModel.vnData?.total?.internal?.treating ?: ""
        binding.tvRecovered.text = viewModel.vnData?.total?.internal?.recovered ?: ""
        binding.tvDeaths.text = viewModel.vnData?.total?.internal?.death ?: ""
    }

    fun updateDataList() {
        viewModel.vnData?.locations?.let { adapter.updateData(it) }
    }

    fun updateDataToday() {
        binding.tvCases.text = viewModel.vnData?.today?.internal?.cases ?: ""
        binding.tvTreating.text = viewModel.vnData?.today?.internal?.treating ?: ""
        binding.tvRecovered.text = viewModel.vnData?.today?.internal?.recovered ?: ""
        binding.tvDeaths.text = viewModel.vnData?.today?.internal?.death ?: ""
    }

    fun settingChart() {
        if (viewModel.vnData?.overview != null) {

            val dataSets: ArrayList<IBarDataSet> = ArrayList()
            val dates = mutableListOf<String>()

            val entriesCases: MutableList<BarEntry> = mutableListOf()
            val entriesRecovered: MutableList<BarEntry> = mutableListOf()
            val entriesDeath: MutableList<BarEntry> = mutableListOf()
            for (data in viewModel.vnData?.overview!!) {
                dates.add(data.date ?: "")
                entriesCases.add(
                    BarEntry(
                        convertDate(DATE_PATTERN_MMDD, data.date ?: "0").toFloat(),
                        data.cases.toString().toFloat()
                    )
                )
                entriesRecovered.add(
                    BarEntry(
                        convertDate(DATE_PATTERN_MMDD, data.date ?: "0").toFloat(),
                        data.recovered.toString().toFloat()
                    )
                )
                entriesDeath.add(
                    BarEntry(
                        convertDate(DATE_PATTERN_MMDD, data.date ?: "0").toFloat(),
                        data.death.toString().toFloat()
                    )
                )
            }
            val dataSetCase =
                BarDataSet(entriesCases, requireContext().getString(R.string.str_cases))
            dataSetCase.color = ContextCompat.getColor(requireContext(), R.color.color_blue)

            val dataSetRecovered =
                BarDataSet(entriesRecovered, requireContext().getString(R.string.str_recovered))
            dataSetRecovered.color = ContextCompat.getColor(requireContext(), R.color.color_green)

            val dataSetDeath =
                BarDataSet(entriesDeath, requireContext().getString(R.string.str_deaths))
            dataSetDeath.color = ContextCompat.getColor(requireContext(), R.color.color_red)

            val xAxis: XAxis = binding.lineChart.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(dates.toTypedArray())
            binding.lineChart.axisLeft.axisMinimum = 0f
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            xAxis.setCenterAxisLabels(true)
            xAxis.isGranularityEnabled = true
            xAxis.setDrawGridLines(false);


            val yAxis: YAxis = binding.lineChart.axisLeft
            yAxis.setDrawGridLines(false)

            dataSets.add(dataSetCase)
            dataSets.add(dataSetRecovered);
            dataSets.add(dataSetDeath)


            val groupCount = 7
            val barSpace = 0f
            val barWidth = 0.16f
            val groupSpace = 0.2f

            val lineData = BarData(dataSets)
            lineData.barWidth = 0.25f
            binding.lineChart.data = lineData

            binding.lineChart.setDrawGridBackground(false)
            val description = Description()
            description.text = ""
            binding.lineChart.description = description

            xAxis.axisMinimum = 0.25f
            xAxis.axisMaximum = dates.size - 0.25f
            binding.lineChart.groupBars(0f, groupSpace, barSpace)

            binding.lineChart.invalidate()

            binding.tvAvgCaces.text = getString(
                R.string.str_avg_cases,
                viewModel.vnData?.overview!![0].avgCases7day
            )
            binding.tvAvgRecovered.text = getString(
                R.string.str_avg_reco,
                viewModel.vnData?.overview!![0].avgRecovered7day
            )
            binding.tvAvgDeaths.text = getString(
                R.string.str_avg_death,
                viewModel.vnData?.overview!![0].avgDeath7day
            )
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

    private class LabelFormatter internal constructor(
        var chart: BarLineChartBase<*>,
        var labels: Array<String>
    ) :
        IAxisValueFormatter {
        override fun getFormattedValue(value: Float, axis: AxisBase): String {
            return labels[value.toInt()]
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