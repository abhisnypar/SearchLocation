package com.example.saiabhinaypidugu.keeptrucking.viewModel

import android.databinding.ObservableField
import com.example.saiabhinaypidugu.keeptrucking.restService.response.SearchListResponse

class SearchListItemViewModel(var citiesResponseList: SearchListResponse) {
    private var cityNameObservableField: ObservableField<String> = ObservableField()
    private var stateNameObservableField: ObservableField<String> = ObservableField()
    private var countryNameObservableField: ObservableField<String> = ObservableField()

    fun setUpLayoutData() {
        cityNameObservableField.set(citiesResponseList.adminName1)
        stateNameObservableField.set(citiesResponseList.name)
        countryNameObservableField.set(citiesResponseList.countryName)
    }

    fun getCityNameObservableField(): ObservableField<String> = cityNameObservableField
    fun getStateNameObservableField(): ObservableField<String> = stateNameObservableField
    fun getCountryNameObservableField(): ObservableField<String> = countryNameObservableField
}