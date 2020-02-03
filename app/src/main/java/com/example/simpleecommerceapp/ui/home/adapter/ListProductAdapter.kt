package com.example.simpleecommerceapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.ListProduct.DataItem
import com.example.simpleecommerceapp.utils.Constants
import com.example.simpleecommerceapp.utils.formatToRupiah
import kotlinx.android.synthetic.main.item_product_category.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListProductAdapter(private var mData: List<DataItem>?, private var listener: onItemClickListener) : RecyclerView.Adapter<ListProductAdapter.ListProductHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ListProductHolder(view)
    }

    override fun getItemCount(): Int {
        return mData?.size ?:0
    }
    //atau override fun getItemCount(): Int = mData?.size ?: 0


    override fun onBindViewHolder(holder: ListProductAdapter.ListProductHolder, position: Int) {
        val item = mData?.get(position)

        holder.nameProduct.text = item?.nama
        holder.priceProduct.text = formatToRupiah(item?.harga ?:"")
        holder.typeProduct.text = item?.kategori

        Glide.with(holder.imgProduct)
            .load(Constants.IMAGE_URL + item?.gambar)
            .into(holder.imgProduct)

        holder.itemView.onClick {
            listener.itemClick(item)
        }
    }


    inner class ListProductHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {


        val imgProduct = itemView.ivProduk
        val nameProduct = itemView.tvNama
        val priceProduct = itemView.tvHarga
        val typeProduct = itemView.tvJenis
    }

    interface onItemClickListener {
        fun itemClick(item: DataItem?)
    }
}




