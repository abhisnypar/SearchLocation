package com.example.saiabhinaypidugu.keeptrucking.context

import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(activity: Activity) {
    private var mActivity: Activity = activity

    @Provides
    fun providesActivity(): Activity {
        return mActivity
    }
}