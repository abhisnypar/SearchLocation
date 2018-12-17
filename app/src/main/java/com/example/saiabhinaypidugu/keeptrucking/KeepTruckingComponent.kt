package com.example.saiabhinaypidugu.keeptrucking

import android.content.Context
import com.example.saiabhinaypidugu.keeptrucking.context.ActivityModule
import com.example.saiabhinaypidugu.keeptrucking.context.ContextModule
import com.example.saiabhinaypidugu.keeptrucking.locationFinder.GeoLocationFinder
import com.example.saiabhinaypidugu.keeptrucking.restService.SearchApi
import com.example.saiabhinaypidugu.keeptrucking.restService.SearchApiModule
import com.example.saiabhinaypidugu.keeptrucking.restService.TruckRxSchedulers
import com.example.saiabhinaypidugu.keeptrucking.scope.AppScope
import com.example.saiabhinaypidugu.keeptrucking.searchDagger.viewModelModule.SearchModule
import dagger.Component
import dagger.android.AndroidInjector

@AppScope
@Component(modules = [SearchModule::class, ContextModule::class, SearchApiModule::class, ActivityModule::class])
interface KeepTruckingComponent : AndroidInjector<KeepTruckingApplication> {

    fun injectActivity(searchActivity: SearchActivity)
    fun injectContext(context: Context)

    fun searchApi(): SearchApi
    fun rxSchedulers(): TruckRxSchedulers
    fun geoLocationFinder(): GeoLocationFinder
}