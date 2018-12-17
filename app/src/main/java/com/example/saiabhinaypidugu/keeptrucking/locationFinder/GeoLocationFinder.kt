package com.example.saiabhinaypidugu.keeptrucking.locationFinder

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.model.LatLng
import rx.Observable


class GeoLocationFinder(var activity: Activity) {

    var lManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    companion object {
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }

    fun getLatLang(): Observable<LatLng>? {
        if (!permissionEnabled()) {
            askForPermission()
        }
        return Observable.just(LatLng(getLocation().latitude, getLocation().longitude))
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
    }

    private fun permissionEnabled(): Boolean =
            ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED


    @SuppressLint("MissingPermission")
    private fun getLocation(): Location {
        return lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    }
}