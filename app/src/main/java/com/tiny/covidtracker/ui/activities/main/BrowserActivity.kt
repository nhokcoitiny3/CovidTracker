package com.tiny.covidtracker.ui.activities.main

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.tiny.covidtracker.constants.Tags
import com.tiny.covidtracker.databinding.ActivityBrowserBinding
import com.tiny.covidtracker.databinding.ActivityProvinceBinding
import com.tiny.covidtracker.di.createGson
import com.tiny.covidtracker.ui.bases.BaseActivity
import com.tiny.covidtracker.ui.bases.BaseViewModel
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrowserActivity : BaseActivity() {
    override val viewModel: MainViewModel by viewModel()

    private val data: String by lazy {
        intent.getStringExtra(Tags.KEY_INTENT_DATA) ?: ""

    }

    companion object {
        fun getIntent(context: Context, data: String): Intent {
            return Intent(context, BrowserActivity::class.java).run {
                putExtra(Tags.KEY_INTENT_DATA,data)
            }
        }
    }

    lateinit var binding: ActivityBrowserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initAction()
    }

    fun initView() {
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.javaScriptCanOpenWindowsAutomatically = true
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        binding.webview.loadUrl(data)
    }

    fun initAction() {
        binding.ivBack.setSafeOnClickListener {
            finish()
        }
    }
}