package com.example.fragmentviewmodeltest.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.fragmentviewmodeltest.viewmodel.AppState
import com.example.fragmentviewmodeltest.R
import com.example.fragmentviewmodeltest.databinding.MainFragmentBinding
import com.example.fragmentviewmodeltest.model.Weather
import com.example.fragmentviewmodeltest.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = MainFragmentBinding.inflate(inflater, container, false)
            val view = binding.root
            return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherFromLocalSource()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                binding.loadingLayout.visibility = View.GONE
                setData(weatherData)
                Snackbar.make(binding.mainView, "Success!", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
                Snackbar.make(binding.mainView, "Loading...", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getLiveData() }
                    .show()
            }
        }

    }

    private fun setData(weatherData: Weather) {
        binding.cityName.text = weatherData.city.city
        binding.cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                weatherData.city.lat.toString(),
                weatherData.city.lon.toString()
        )
        binding.temperatureValue.text = weatherData.temperature.toString()
        binding.feelsLikeValue.text = weatherData.feelsLike.toString()
    }

//    private fun push () {
//        Toast.makeText(context, "new", Toast.LENGTH_LONG).show()
//    }

}
