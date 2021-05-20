package com.example.fragmentviewmodeltest.ui.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentviewmodeltest.MainActivity
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData()) :
        ViewModel() {

    var counter : Int = 0

    fun getData(): LiveData<Any> {
        getDataFromLocalSource()
        return liveDataToObserve
    }

    private fun getDataFromLocalSource() {
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(counter)
            liveDataToObserve.postValue(Any())
        }.start()
    }

    fun userClicked() {
        counter++
    }

}