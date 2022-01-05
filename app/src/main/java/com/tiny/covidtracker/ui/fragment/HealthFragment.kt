package com.tiny.covidtracker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tiny.covidtracker.databinding.FragmentDeclarationBinding
import com.tiny.covidtracker.databinding.FragmentHealthBinding
import com.tiny.covidtracker.ui.activities.main.MainViewModel
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import android.content.Intent
import android.net.Uri


class HealthFragment : Fragment() {
    val viewModel: MainViewModel by sharedViewModel()


    lateinit var binding: FragmentHealthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHealthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.tvPhone1.setSafeOnClickListener {
            val number = "tel:" + binding.tvPhone1.text.toString().trim()
            val callIntent = Intent(Intent.ACTION_CALL, Uri.parse(number))
            startActivity(callIntent)
        }
    }
}