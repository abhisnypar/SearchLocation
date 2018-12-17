package com.example.saiabhinaypidugu.keeptrucking.restService

import rx.Scheduler

interface RxSchedulers {
    fun io(): Scheduler
    fun androidThread():Scheduler
    fun internet():Scheduler
}