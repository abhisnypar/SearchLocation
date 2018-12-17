package com.example.saiabhinaypidugu.keeptrucking.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.saiabhinaypidugu.keeptrucking.databinding.LayoutCitiesListItemBinding

class SearchRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: LayoutCitiesListItemBinding = DataBindingUtil.bind(itemView)!!

}