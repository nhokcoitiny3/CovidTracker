package com.tiny.covidtracker.ui.adapter

import DataCountryResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tiny.covidtracker.databinding.ItemCountry2Binding
import com.tiny.covidtracker.ui.utils.Utils
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener

class Country2Adapter(val listDatas: MutableList<DataCountryResponse> = mutableListOf(), val onSelect: (code: String) -> Unit) :
    RecyclerView.Adapter<Country2Adapter.ViewHolder>() {

    fun updateData(listDatas: List<DataCountryResponse>) {
        this.listDatas.clear()
        this.listDatas.addAll(listDatas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCountry2Binding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listDatas[position])
        holder.itemCountryBinding.root.setSafeOnClickListener {
            onSelect.invoke(listDatas[position].iso2)
        }
    }

    override fun getItemCount() = listDatas.size

    inner class ViewHolder(val itemCountryBinding: ItemCountry2Binding) :
        RecyclerView.ViewHolder(itemCountryBinding.root) {

        fun bindData(data: DataCountryResponse) {
            itemCountryBinding.tvName.text = data.country
            Utils.g().providePicasso(itemView.context)
                .load(data.flag)
                .into(itemCountryBinding.ivFlag)
        }
    }

}