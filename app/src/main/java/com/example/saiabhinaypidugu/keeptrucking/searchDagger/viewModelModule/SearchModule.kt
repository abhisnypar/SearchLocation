package com.example.saiabhinaypidugu.keeptrucking.searchDagger.viewModelModule

import android.app.Activity
import android.content.Context
import com.example.saiabhinaypidugu.keeptrucking.locationFinder.GeoLocationFinder
import com.example.saiabhinaypidugu.keeptrucking.restService.SearchApi
import com.example.saiabhinaypidugu.keeptrucking.restService.TruckRxSchedulers
import com.example.saiabhinaypidugu.keeptrucking.scope.AppScope
import com.example.saiabhinaypidugu.keeptrucking.viewModel.SearchListViewModel
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class SearchModule {

    companion object {
        const val BASE_URL = "http://api.geonames.org/"
    }

    @Provides
    @AppScope
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    @AppScope
    fun providesRxAdapter(): RxJavaCallAdapterFactory {
        return RxJavaCallAdapterFactory.createWithScheduler(TruckRxSchedulers.INTERNET_SCHEDULERS)
    }


    @Provides
    @AppScope
    fun providesGsonClient(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesViewModel(context: Context, searchApi: SearchApi, rxSchedulers: TruckRxSchedulers, geoLocationFinder: GeoLocationFinder): SearchListViewModel {
        return SearchListViewModel(context, searchApi, rxSchedulers, geoLocationFinder)
    }

    @Provides
    fun providesGeoLocationFinder(activity: Activity): GeoLocationFinder {
        return GeoLocationFinder(activity = activity)
    }
}