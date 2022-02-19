package com.example.hoangcv2_assiagnment.adapter

import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.hoangcv2_assiagnment.ImageRequestAsk
import com.example.hoangcv2_assiagnment.OnItemClickListener
import com.example.hoangcv2_assiagnment.R
import com.example.hoangcv2_assiagnment.Status
import com.example.hoangcv2_assiagnment.model.Product
import java.util.*


class ProductAdapter(private var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {
    private var list: MutableList<Product>
    fun getAll(list: MutableList<Product>?) {
        this.list = list!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.product_custom_view, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product: Product = list[position]
        holder.txtTitle1.text = product.productName
        holder.txtPrice.text = "$" + product.productPrice.toString()
        val photo = ImageRequestAsk().execute(product.productImage).get()!!
        holder.imgViewItem.setImageBitmap(photo)
        //convert bit photo to android resources
        val background = ImageRequestAsk().execute(product.productBackground).get()!!
        holder.backgroundItem.background =
            BitmapDrawable(holder.itemView.context.resources, background)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(holder.adapterPosition, Status.DETAIL)
        }
        holder.itemView.setOnLongClickListener{
            onItemClickListener.onItemClick(holder.adapterPosition,Status.ADD_TO_CART)
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle1: TextView = itemView.findViewById(R.id.txtTitleItem)
        var txtPrice: TextView = itemView.findViewById(R.id.txtPriceItem)
        var imgViewItem: ImageView = itemView.findViewById(R.id.imageViewItem)
        var backgroundItem: ConstraintLayout = itemView.findViewById(R.id.backgroundItem)

    }

    init {
        list = ArrayList<Product>()
    }
}