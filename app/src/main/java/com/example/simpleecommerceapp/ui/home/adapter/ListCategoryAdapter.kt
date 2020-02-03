package com.example.simpleecommerceapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.Category.DataItem
import com.example.simpleecommerceapp.utils.Constants
import kotlinx.android.synthetic.main.item_category.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListCategoryAdapter(private var mData: List<DataItem>?, private var listener: itemClickListener) : RecyclerView.Adapter<ListCategoryAdapter.ListCategoryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ListCategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return ListCategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return mData?.size ?:0
    }

    override fun onBindViewHolder(holder: ListCategoryHolder, position: Int) {
        val item = mData?.get(position)

        holder.nameCategory.text = item?.kategori

        Glide.with(holder.imgCategory)
            .load(Constants.IMAGE_URL + item?.gambar)
            .into(holder.imgCategory)

        holder.itemView.onClick {
            listener.itemClick(item)
        }

    }

    class ListCategoryHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val imgCategory = itemView.ivKategori
        val nameCategory = itemView.tvKategori
    }

    interface itemClickListener {
        fun itemClick(item: DataItem?)
    }
}