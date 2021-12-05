package com.tiny.covidtracker.ui.bases

import android.text.TextUtils
import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.*
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                onError()
            }
        }
    }


    open fun onError() {
    }

    fun hideLoading() {
        isLoading.value = false
    }

    fun showLoading() {
        isLoading.value = true
    }

    val network = viewModelScope + exceptionHandler


    var listBlock = arrayListOf<suspend CoroutineScope.() -> Unit>()

    fun launch(block: suspend CoroutineScope.() -> Unit): Job {
        showLoading()

        listBlock.add(block)

        return network.launch {
            try {
                block.invoke(network)
            } catch (e: Exception) {
                Log.wtf("EX", e)
                throw e
            } finally {
                listBlock.remove(block)
                withContext(Dispatchers.Main) {
                    hideLoading()
                }
                return@launch
            }

        }
    }

    fun launchBackground(block: suspend CoroutineScope.() -> Unit): Job {
        return network.launch {
            try {
                block.invoke(network)
            } catch (e: Exception) {
                Log.wtf("EX", e)
            }
            return@launch
        }
    }

    fun launchSilent(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch {
            try {
                block.invoke(viewModelScope)
            } catch (e: Exception) {
                Log.wtf("EX", e)
            }
            return@launch
        }
    }

    fun launchIO(block: suspend CoroutineScope.() -> Unit): Job {
        showLoading()

        listBlock.add(block)

        return network.launch(Dispatchers.IO) {
            try {
                block.invoke(network)
            } catch (e: Exception) {
                Log.wtf("EX", e)
                throw e
            } finally {
                listBlock.remove(block)
                if (listBlock.isEmpty())
                    withContext(Dispatchers.Main) {
                        hideLoading()
                    }
            }

            return@launch
        }
    }

}