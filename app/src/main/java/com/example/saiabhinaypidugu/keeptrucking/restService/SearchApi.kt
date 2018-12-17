package com.example.saiabhinaypidugu.keeptrucking.restService

import com.example.saiabhinaypidugu.keeptrucking.restService.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface SearchApi {

    @GET("searchJSON")
    fun getListResponse(@Query("name_starts_With") nameStartsWith: String,
                        @Query("maxRows") maxRows: Int = 10,
                        @Query("username") userName: String = "keep_truckin"
    ): Observable<SearchResponse>

    @GET("findNearbyPlaceNameJSON")
    fun getListResponseWithLatLang(@Query("lat") latitude: Double,
                                   @Query("lng") longitude: Double,
                                   @Query("username") userName: String = "keep_truckin"
    ): Observable<SearchResponse>
}