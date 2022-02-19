package com.example.hoangcv2_assiagnment.adapter

import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.hoangcv2_assiagnment.ImageRequestAsk
import com.example.hoangcv2_assiagnment.OnItemClickListener
import com.example.hoangcv2_assiagnment.R
import com.example.hoangcv2_assiagnment.Status
import com.example.hoangcv2_assiagnment.model.Cart
import java.util.*


class CartAdapter(private var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CartAdapter.ItemViewHolder>() {
    private var list: MutableList<Cart>
    fun getAll(list: MutableList<Cart>?) {
        this.list = list!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.cart_item_view, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemCart: Cart = list[position]
        holder.txtTitle1.text = itemCart.productName
        holder.txtPrice1.text = "$" + itemCart.productPrice.toString()
        val photo = ImageRequestAsk().execute(itemCart.productImage).get()!!
        holder.imgViewItem.setImageBitmap(photo)
        val background = ImageRequestAsk().execute(itemCart.productBackground).get()!!
        holder.imgViewItem.background =
            BitmapDrawable(holder.itemView.context.resources, background)
        holder.btnDelete.setOnClickListener {
            onItemClickListener.onItemClick(holder.adapterPosition,Status.DELETE_ITEM_CART)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle1: TextView = itemView.findViewById(R.id.txtTitleItem)
        var txtPrice1: TextView = itemView.findViewById(R.id.txtPriceItem)
        var imgViewItem: ImageView = itemView.findViewById(R.id.imageViewItem)
        var btnDelete: Button=itemView.findViewById(R.id.btnDelete)
    }

    init {
        list = ArrayList<Cart>()
    }
}