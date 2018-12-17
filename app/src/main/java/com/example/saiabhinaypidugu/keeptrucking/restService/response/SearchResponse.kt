package com.example.saiabhinaypidugu.keeptrucking.restService.response

import android.os.Parcel
import android.os.Parcelable

data class SearchResponse(
        val geonames: List<SearchListResponse>
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(SearchListResponse))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(geonames)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchResponse> {
        override fun createFromParcel(parcel: Parcel): SearchResponse {
            return SearchResponse(parcel)
        }

        override fun newArray(size: Int): Array<SearchResponse?> {
            return arrayOfNulls(size)
        }
    }

}