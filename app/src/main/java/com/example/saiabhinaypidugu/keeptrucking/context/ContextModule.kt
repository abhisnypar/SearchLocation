package com.example.saiabhinaypidugu.keeptrucking.context

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class ContextModule(context: Context) {
    private var mContext: Context = context

    @Provides
    fun providesContext(): Context {
        return mContext
    }
}