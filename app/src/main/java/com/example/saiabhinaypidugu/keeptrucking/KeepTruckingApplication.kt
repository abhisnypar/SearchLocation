package com.example.saiabhinaypidugu.keeptrucking

import android.app.Application
import com.example.saiabhinaypidugu.keeptrucking.context.ActivityModule
import com.example.saiabhinaypidugu.keeptrucking.context.ContextModule
import com.example.saiabhinaypidugu.keeptrucking.searchDagger.viewModelModule.SearchModule


class KeepTruckingApplication : Application() {

    private lateinit var keepTruckingComponent: KeepTruckingComponent

    override fun onCreate() {
        super.onCreate()
        keepTruckingComponent = DaggerKeepTruckingComponent.builder()
                .contextModule(ContextModule(this))
                .searchModule(SearchModule())
                .activityModule(ActivityModule(SearchActivity()))
                .build()
        keepTruckingComponent.inject(this)

    }
}