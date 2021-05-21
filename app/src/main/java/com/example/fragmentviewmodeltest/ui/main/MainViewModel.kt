package com.example.fragmentviewmodeltest.ui.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentviewmodeltest.AppState
import com.example.fragmentviewmodeltest.MainActivity
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) :
        ViewModel() {

    //var counter : Int = 0

    fun getLiveData() = liveDataToObserve
    fun getWeather() = getDataFromLocalSource()


    fun getData(): LiveData<AppState> {
        getDataFromLocalSource()
        return liveDataToObserve
    }

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(5000)
            //liveDataToObserve.postValue(counter)
            liveDataToObserve.postValue(AppState.Success(Any()))
        }.start()
    }

    fun userClicked() {
        //counter++
    }

}