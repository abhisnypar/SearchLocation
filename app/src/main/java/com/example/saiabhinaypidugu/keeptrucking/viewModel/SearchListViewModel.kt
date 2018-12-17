package com.example.saiabhinaypidugu.keeptrucking.viewModel

import android.content.Context
import android.databinding.ObservableField
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.saiabhinaypidugu.keeptrucking.adapter.SearchRecyclerAdapter
import com.example.saiabhinaypidugu.keeptrucking.locationFinder.GeoLocationFinder
import com.example.saiabhinaypidugu.keeptrucking.restService.SearchApi
import com.example.saiabhinaypidugu.keeptrucking.restService.TruckRxSchedulers
import com.example.saiabhinaypidugu.keeptrucking.restService.response.SearchListResponse
import com.example.saiabhinaypidugu.keeptrucking.restService.response.SearchResponse
import com.google.android.gms.maps.model.LatLng
import rx.Subscriber


class SearchListViewModel(var context: Context, private var searchApi: SearchApi, private var rxSchedulers: TruckRxSchedulers, var geoLocationFinder: GeoLocationFinder) {

    var latLang: LatLng? = null

    private var searchRecyclerAdapter: SearchRecyclerAdapter? = null
    private var recyclerViewLayoutManager: ObservableField<RecyclerView.LayoutManager> = ObservableField()
    private var recyclerViewAdapter: ObservableField<SearchRecyclerAdapter> = ObservableField()
    private var searchCitiesTextField: ObservableField<String> = ObservableField()
    private var citiesResponseList: ArrayList<SearchListResponse> = ArrayList()

    fun onSearchButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            getListOfCities()
        }
    }

    fun onGpsButtonClicked(): View.OnClickListener {
        return View.OnClickListener {
            searchRecyclerAdapter?.let {
                searchRecyclerAdapter?.clear()
            }
            getLatLangOfCurrentLocation()
        }
    }

    fun getRecyclerViewLayoutManagerObservableField(): ObservableField<RecyclerView.LayoutManager> = recyclerViewLayoutManager

    fun getRecyclerViewAdapterObservableField(): ObservableField<SearchRecyclerAdapter> = recyclerViewAdapter
    fun getSearchCitiesTextField(): ObservableField<String> = searchCitiesTextField


    private fun getLatLangOfCurrentLocation() {
        geoLocationFinder.getLatLang()?.let {
            it.observeOn(rxSchedulers.androidThread())
                    .subscribeOn(rxSchedulers.internet())
                    .subscribe(object : Subscriber<LatLng>() {
                        override fun onCompleted() {
                            getListOfCitiesWithGps()
                        }

                        override fun onError(e: Throwable?) {
                            Log.d("TAG", e?.message)
                        }

                        override fun onNext(t: LatLng?) {
                            latLang = t
                        }
                    })
        }
    }

    private fun getListOfCitiesWithGps() {
        searchApi.getListResponseWithLatLang(latitude = latLang?.latitude!!, longitude = latLang?.longitude!!)
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.internet())
                .subscribe(object : Subscriber<SearchResponse>() {
                    override fun onCompleted() {
                        setUpRecyclerView()
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("On List Response Error", e?.message)
                    }

                    override fun onNext(response: SearchResponse) {
                        citiesResponseList.addAll(response.geonames)
                    }
                })

    }

    private fun setUpRecyclerView() {
        recyclerViewLayoutManager.set(LinearLayoutManager(context))
        searchRecyclerAdapter = recyclerViewAdapter.get()

        if (searchRecyclerAdapter == null) {
            recyclerViewAdapter.set(SearchRecyclerAdapter(citiesResponseList, context))
        }
        searchRecyclerAdapter?.notifyDataSetChanged()
    }

    private fun getListOfCities() {
        searchCitiesTextField.get()?.let {
            searchApi.getListResponse(nameStartsWith = it).observeOn(rxSchedulers.androidThread())
                    .subscribeOn(rxSchedulers.internet())
                    .subscribe(object : Subscriber<SearchResponse>() {
                        override fun onCompleted() {
                            setUpRecyclerView()
                        }

                        override fun onError(e: Throwable?) {
                            Log.d("On List Response Error", e?.message)
                        }

                        override fun onNext(response: SearchResponse) {
                            citiesResponseList.addAll(response.geonames)
                        }
                    })
        }
    }

}