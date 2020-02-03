package com.example.simpleecommerceapp.ui.productByCategory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.ListProduct.DataItem
import com.example.simpleecommerceapp.ui.home.adapter.ListProductAdapter
import com.example.simpleecommerceapp.utils.Constants
import com.example.simpleecommerceapp.utils.formatToRupiah
import kotlinx.android.synthetic.main.item_product_category.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListProductByCategoryAdapter(private var mData: List<DataItem>?, private var listener: onItemClickListener): RecyclerView.Adapter<ListProductByCategoryAdapter.ListProductByCategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductByCategoryAdapter.ListProductByCategoryHolder {
        val view = ListProductByCategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_category,parent,false))
        return view
    }

    override fun getItemCount(): Int = mData?.size ?:0

    override fun onBindViewHolder(holder: ListProductByCategoryHolder, position: Int) {
        val item = mData?.get(position)

        holder.mCategoryProductName.text = item?.nama
        holder.mPriceProductCategory.text = formatToRupiah(item?.harga ?:"")
        holder.mTypeProductCategory.text = item?.kategori

        Glide.with(holder.imgProductCategory)
            .load(Constants.IMAGE_URL + item?.gambar)
            .into(holder.imgProductCategory)

        holder.itemView.onClick {
            listener.itemClick(item)
        }
    }

    inner class ListProductByCategoryHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProductCategory = itemView.ivProduk
        val mCategoryProductName = itemView.tvNama
        val mPriceProductCategory = itemView.tvHarga
        val mTypeProductCategory = itemView.tvJenis
    }

    interface onItemClickListener {
        fun itemClick(item: DataItem?)
    }


}