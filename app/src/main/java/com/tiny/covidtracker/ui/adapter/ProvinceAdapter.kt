package com.tiny.covidtracker.ui.adapter

import DataGlobalResponse
import LocationEntityResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tiny.covidtracker.databinding.ItemCountryBinding
import com.tiny.covidtracker.databinding.ItemProvinceBinding
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener

class ProvinceAdapter(
    val listDatas: MutableList<LocationEntityResponse> = mutableListOf(),
    val onSelect: (item: LocationEntityResponse) -> Unit
) :
    RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {

    fun updateData(listDatas: List<LocationEntityResponse>) {
        this.listDatas.clear()
        this.listDatas.addAll(listDatas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemProvinceBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listDatas[position])
        holder.itemView.setSafeOnClickListener {
            onSelect.invoke(listDatas[position])
        }
    }

    override fun getItemCount() = listDatas.size

    inner class ViewHolder(val binding: ItemProvinceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: LocationEntityResponse) {
            binding.tvName.text = data.name
            binding.tvToday.text = data.casesToday?.replace("  ", "")?.trim()
            binding.tvTotal.text = data.cases?.trim()
        }
    }

}