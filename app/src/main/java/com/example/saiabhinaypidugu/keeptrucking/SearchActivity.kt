package com.example.saiabhinaypidugu.keeptrucking

import android.Manifest
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.saiabhinaypidugu.keeptrucking.context.ActivityModule
import com.example.saiabhinaypidugu.keeptrucking.context.ContextModule
import com.example.saiabhinaypidugu.keeptrucking.databinding.ActivitySearchBinding
import com.example.saiabhinaypidugu.keeptrucking.viewModel.SearchListViewModel
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: SearchListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerKeepTruckingComponent
                .builder()
                .contextModule(ContextModule(this))
                .activityModule(ActivityModule(this))
                .build()
                .injectActivity(this)
        super.onCreate(savedInstanceState)
        if (!permissionEnabled()) {
            askForPermission()
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val dataBindingUtil: ActivitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        dataBindingUtil.viewModel = viewModel
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1)
    }

    private fun permissionEnabled(): Boolean =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

}
