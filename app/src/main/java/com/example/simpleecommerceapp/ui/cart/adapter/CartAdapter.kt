package com.example.simpleecommerceapp.ui.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.Cart.DataItem
import com.example.simpleecommerceapp.ui.productByCategory.adapter.ListProductByCategoryAdapter
import com.example.simpleecommerceapp.utils.Constants
import com.example.simpleecommerceapp.utils.formatToRupiah
import kotlinx.android.synthetic.main.item_cart.view.*
import kotlinx.android.synthetic.main.item_product_category.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class CartAdapter(private var mData: List<DataItem>?,private var cartListener: onItemCartClickListener): RecyclerView.Adapter<CartAdapter.CartHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false)
        return CartHolder(view)
    }

    override fun getItemCount(): Int {
        return mData?.size ?:0
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val item = mData?.get(position)

        holder.nameProductCart.text = item?.nama
        holder.priceProductCart.text = formatToRupiah(item?.harga ?:"")
        holder.typeProductCart.text = item?.kategori

        Glide.with(holder.imgProductCart)
            .load(Constants.IMAGE_URL + item?.gambar)
            .into(holder.imgProductCart)

        holder.itemView.onClick {
            cartListener.itemClick(item)
        }

        holder.deleteItemCart.onClick {
            cartListener.delItem(item)
        }


    }

    inner class CartHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProductCart = itemView.ivProdukCart
        val nameProductCart = itemView.tvNamaItemCart
        val priceProductCart = itemView.tvHargaItemCart
        val typeProductCart = itemView.tvJenisItemCart
        val deleteItemCart = itemView.ivDeleteCart
    }
}

interface onItemCartClickListener {
    fun itemClick(item: DataItem?)
    fun delItem(item: DataItem?)
}
