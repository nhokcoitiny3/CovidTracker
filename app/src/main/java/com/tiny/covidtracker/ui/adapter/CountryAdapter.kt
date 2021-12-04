package com.tiny.covidtracker.ui.adapter

import DataGlobalResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tiny.covidtracker.databinding.ItemCountryBinding

class CountryAdapter(val listDatas: MutableList<DataGlobalResponse> = mutableListOf()) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    fun updateData(listDatas: List<DataGlobalResponse>) {
        this.listDatas.clear()
        this.listDatas.addAll(listDatas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listDatas[position])
    }

    override fun getItemCount() = listDatas.size

    inner class ViewHolder(val itemCountryBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(itemCountryBinding.root) {

        fun bindData(data: DataGlobalResponse) {
            itemCountryBinding.tvName.text = data.country
            itemCountryBinding.tvTotal.text = data.todayCases
        }
    }

}