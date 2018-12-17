package com.example.saiabhinaypidugu.keeptrucking.restService.response

import android.os.Parcel
import android.os.Parcelable

data class SearchListResponse(
        val adminCode1: String,
        val lng: String,
        val geonameId: Long,
        val toponymName: String,
        val countryId: String,
        val fcl: String,
        val population: Int,
        val countryCode: String,
        val name: String,
        val fclName: String,
        val countryName: String,
        val fcodeName: String,
        val adminName1: String,
        val lat: String,
        val fcode: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(adminCode1)
        parcel.writeString(lng)
        parcel.writeLong(geonameId)
        parcel.writeString(toponymName)
        parcel.writeString(countryId)
        parcel.writeString(fcl)
        parcel.writeInt(population)
        parcel.writeString(countryCode)
        parcel.writeString(name)
        parcel.writeString(fclName)
        parcel.writeString(countryName)
        parcel.writeString(fcodeName)
        parcel.writeString(adminName1)
        parcel.writeString(lat)
        parcel.writeString(fcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchListResponse> {
        override fun createFromParcel(parcel: Parcel): SearchListResponse {
            return SearchListResponse(parcel)
        }

        override fun newArray(size: Int): Array<SearchListResponse?> {
            return arrayOfNulls(size)
        }
    }
}