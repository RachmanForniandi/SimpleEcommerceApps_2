package com.example.simpleecommerceapp.ui.home.adapter

import android.content.ContentValues
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.DataItem
import com.example.simpleecommerceapp.utils.Constants
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_image_slider.view.*

class SliderAdapter (private val mValues: List<DataItem>?): SliderViewAdapter<SliderAdapter.ViewHolder>(){

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
    }

    inner class ViewHolder (val itemView: View):SliderViewAdapter.ViewHolder(itemView){
        val mImage = itemView.iv_auto_image_slider
    }
}