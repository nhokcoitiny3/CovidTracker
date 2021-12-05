package com.tiny.covidtracker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tiny.covidtracker.databinding.FragmentNewsBinding
import com.tiny.covidtracker.ui.activities.main.BrowserActivity
import com.tiny.covidtracker.ui.activities.main.MainViewModel
import com.tiny.covidtracker.ui.adapter.NewsAdapter
import com.tiny.covidtracker.ui.entites.CommonEntity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewsFragment : Fragment() {
    val viewModel: MainViewModel by sharedViewModel()

    lateinit var binding: FragmentNewsBinding

    val adapter = NewsAdapter {
        startActivity(BrowserActivity.getIntent(requireContext(), it.getDescript()?:""))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        Thread {
            viewModel.getNews()
        }.start()
    }

    fun initView() {
        binding.rvNews.adapter = adapter
    }

    fun updateData(listData: MutableList<CommonEntity>) {
        adapter.updateData(listDatas = listData)
    }
}