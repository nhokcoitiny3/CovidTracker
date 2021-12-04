package com.tiny.covidtracker.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.tiny.covidtracker.databinding.FragmentDeclarationBinding
import com.tiny.covidtracker.ui.activities.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DeclarationFragment : Fragment() {
    val viewModel: MainViewModel by sharedViewModel()


    lateinit var binding: FragmentDeclarationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDeclarationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initView() {
        binding.wbContainer.settings.javaScriptEnabled = true
        binding.wbContainer.settings.javaScriptCanOpenWindowsAutomatically = true
        binding.wbContainer.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                viewModel.showLoading()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                viewModel.hideLoading()


            }
        }
        binding.wbContainer.loadUrl("https://tokhaiyte.vn/")
    }

}