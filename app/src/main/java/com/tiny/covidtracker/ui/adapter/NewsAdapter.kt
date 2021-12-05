package com.tiny.covidtracker.ui.adapter

import DataGlobalResponse
import LocationEntityResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tiny.covidtracker.databinding.ItemCountryBinding
import com.tiny.covidtracker.databinding.ItemNewsBinding
import com.tiny.covidtracker.databinding.ItemProvinceBinding
import com.tiny.covidtracker.ui.entites.CommonEntity
import com.tiny.covidtracker.ui.utils.Utils
import com.tiny.covidtracker.ui.utils.setSafeOnClickListener

class NewsAdapter(
    val listDatas: MutableList<CommonEntity> = mutableListOf(),
    val onSelect: (item: CommonEntity) -> Unit
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    fun updateData(listDatas: List<CommonEntity>) {
        this.listDatas.clear()
        this.listDatas.addAll(listDatas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemNewsBinding.inflate(
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

    inner class ViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: CommonEntity) {
            binding.tvTitle.text = data.getTitle()
            Utils.g().providePicasso(itemView.context)
                .load(data.getImg())
                .into(binding.ivThumb)
        }
    }

}