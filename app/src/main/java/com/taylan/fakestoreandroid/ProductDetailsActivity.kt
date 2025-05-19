package com.taylan.fakestoreandroid

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleView: TextView
    private lateinit var priceView: TextView
    private lateinit var ratingView: TextView
    private lateinit var descriptionView: TextView

    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        imageView = findViewById(R.id.productImageView)
        titleView = findViewById(R.id.productTitle)
        priceView = findViewById(R.id.productPrice)
        ratingView = findViewById(R.id.productRating)
        descriptionView = findViewById(R.id.productDescription)

        val json = intent.getStringExtra("productJson")
        val product: Product? = if (json != null) {
            Gson().fromJson(json, Product::class.java)
        } else {
            null
        }

        if (product != null) {
            populateUI(product)
        } else if (id != -1) {
            ProductService.fetchProductById(id) { fetchedProduct ->
                fetchedProduct?.let {
                    runOnUiThread {
                        populateUI(it)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateUI(product: Product) {
        titleView.text = product.title
        priceView.text = "$${product.price}"
        ratingView.text = "⭐ ${product.rating.rate} (${product.rating.count})"
        descriptionView.text = product.description

        Glide.with(this)
            .load(product.image)
            .into(imageView)
    }
}