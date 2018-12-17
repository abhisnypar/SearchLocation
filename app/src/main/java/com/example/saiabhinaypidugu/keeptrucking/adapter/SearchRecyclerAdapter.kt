package com.example.saiabhinaypidugu.keeptrucking.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.saiabhinaypidugu.keeptrucking.R
import com.example.saiabhinaypidugu.keeptrucking.restService.response.SearchListResponse
import com.example.saiabhinaypidugu.keeptrucking.viewModel.SearchListItemViewModel

class SearchRecyclerAdapter(var citiesResponseList: ArrayList<SearchListResponse>, var context: Context) : RecyclerView.Adapter<SearchRecyclerViewHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, position: Int): SearchRecyclerViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_cities_list_item, view, false)
        return SearchRecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int = citiesResponseList.size


    override fun onBindViewHolder(viewHolder: SearchRecyclerViewHolder, position: Int) {
        citiesResponseList.let {
            val citiesListItemViewModel = SearchListItemViewModel(citiesResponseList[position])
            viewHolder.binding.viewModel = citiesListItemViewModel
            citiesListItemViewModel.setUpLayoutData()
        }
    }

    fun clear() {
        this.citiesResponseList.clear()
        notifyDataSetChanged()
    }
}