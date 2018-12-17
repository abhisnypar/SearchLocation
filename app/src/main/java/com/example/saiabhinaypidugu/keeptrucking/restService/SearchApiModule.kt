package com.example.saiabhinaypidugu.keeptrucking.restService

import com.example.saiabhinaypidugu.keeptrucking.scope.AppScope
import com.example.saiabhinaypidugu.keeptrucking.searchDagger.viewModelModule.SearchModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@AppScope
class SearchApiModule {

    @Provides
    @AppScope
    fun providesSearchApi(okHttpClient: OkHttpClient, gsonClient: GsonConverterFactory, rxAdapter: RxJavaCallAdapterFactory): SearchApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(SearchModule.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonClient)
                .addCallAdapterFactory(rxAdapter)
                .build()
        return retrofit.create(SearchApi::class.java)
    }
}