package com.taylan.fakestoreandroid

import SpacingItemDecoration
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : ComponentActivity() {

    private lateinit var horizontalRecyclerView: RecyclerView
    private lateinit var verticalRecyclerView: RecyclerView

    private lateinit var horizontalAdapter: ProductAdapter
    private lateinit var verticalAdapter: ProductAdapter

    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        horizontalRecyclerView = findViewById(R.id.horizontalRecyclerView)
        verticalRecyclerView = findViewById(R.id.verticalRecyclerView)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        horizontalAdapter = ProductAdapter(emptyList()) { product ->
            openDetails(product)
        }

        verticalAdapter = ProductAdapter(emptyList()) { product ->
            openDetails(product)
        }

        horizontalRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalAdapter
            addItemDecoration(HorizontalSpacingItemDecoration(16, 12)) // 16dp horizontal, 12dp vertical
        }

        verticalRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = verticalAdapter
            verticalRecyclerView.addItemDecoration(SpacingItemDecoration(16, 40)) // 16dp spacing between columns
        }

        viewModel.horizontalProducts.observe(this) { products ->
            horizontalAdapter.updateData(products)
        }

        viewModel.verticalProducts.observe(this) { products ->
            verticalAdapter.updateData(products)
        }

        viewModel.loadProducts()
    }

    private fun openDetails(product: Product) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        val productJson = Gson().toJson(product)
        intent.putExtra("productJson", productJson)
        startActivity(intent)
    }
}