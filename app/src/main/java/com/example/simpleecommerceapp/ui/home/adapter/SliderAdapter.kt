package com.example.simpleecommerceapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.ListProductPromo.DataItem
import com.example.simpleecommerceapp.utils.Constants
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_image_slider.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class SliderAdapter (private val mValues: List<DataItem>?,private val onItemClickListener: itemClickListener): SliderViewAdapter<SliderAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val viewHolder = ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_image_slider,parent,false))
        return viewHolder
    }

    override fun getCount(): Int = mValues?.size?:0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues?.get(position)

        Glide.with(holder?.mImage!!)
            .load(Constants.IMAGE_URL+item?.gambar)
            .into(holder.mImage)

        holder.mImage.onClick {
            onItemClickListener.itemClick(item)
        }
    }

    inner class ViewHolder (val itemView: View):SliderViewAdapter.ViewHolder(itemView){
        val mImage = itemView.iv_auto_image_slider
    }

    interface itemClickListener{
        fun itemClick(dataItem: DataItem?)
    }


}