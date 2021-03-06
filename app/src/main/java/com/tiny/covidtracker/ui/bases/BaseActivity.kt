package com.tiny.covidtracker.ui.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tiny.covidtracker.ui.dialogs.LoadingDialog
import com.tiny.covidtracker.di.Common
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {
    val loading by inject<LoadingDialog>()

    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Common.currentActivity = this
        viewModel?.apply {
            isLoading.observe(this@BaseActivity, {
                handleShowLoading(it!!)
            })
                   }
    }


    override fun onResume() {
        super.onResume()
        Common.currentActivity = this
    }

    fun onError(it: String?) {
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    open fun handleShowLoading(isLoading: Boolean) {
        runOnUiThread {
            if (isLoading) showLoading() else hideLoading()
        }
    }

    fun hideLoading() {
        loading.dismiss()
    }

    fun showLoading() {
        loading.show()
    }


}
