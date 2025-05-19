package com.taylan.fakestoreandroid
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(
    private var products: List<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    fun updateData(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.productImageView)
        val titleView: TextView = view.findViewById(R.id.titleTextView)
        val priceView: TextView = view.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.titleView.text = product.title
        "$${product.price}".also { holder.priceView.text = it }

        Glide.with(holder.imageView.context)
            .load(product.image)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            onClick(product)
        }
    }

    override fun getItemCount(): Int = products.size
}