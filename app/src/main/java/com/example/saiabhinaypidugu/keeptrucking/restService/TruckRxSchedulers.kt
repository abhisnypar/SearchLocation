package com.example.saiabhinaypidugu.keeptrucking.restService

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class TruckRxSchedulers @Inject constructor() : RxSchedulers {

    companion object {
        private val internetExecutor: Executor = Executors.newCachedThreadPool()
        val INTERNET_SCHEDULERS: Scheduler = Schedulers.from(internetExecutor)
    }

    override fun androidThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun internet(): Scheduler = INTERNET_SCHEDULERS

    override fun io(): Scheduler = Schedulers.io()
}