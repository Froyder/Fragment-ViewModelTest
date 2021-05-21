package com.example.fragmentviewmodeltest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentviewmodeltest.model.Repository
import com.example.fragmentviewmodeltest.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
        private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
        private val repositoryImpl: Repository = RepositoryImpl())
    : ViewModel() {

    //var counter : Int = 0

    fun getLiveData() = liveDataToObserve
    fun getWeatherFromLocalSource() = getDataFromLocalSource()
    fun getWeatherFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(3000)
            //liveDataToObserve.postValue(counter)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
        }.start()
    }

    fun userClicked() {
        //counter++
    }

}