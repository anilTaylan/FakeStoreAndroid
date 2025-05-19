package com.taylan.fakestoreandroid
import android.os.Handler
import android.os.Looper
import org.json.JSONObject
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object ProductService {

    fun fetchProducts(limit: Int, callback: (List<Product>) -> Unit) {
        val url = URL("https://fakestoreapi.com/products?limit=$limit")
        thread {
            try {
                val conn = url.openConnection() as HttpURLConnection
                conn.connectTimeout = 5000
                conn.readTimeout = 5000
                conn.requestMethod = "GET"

                val response = conn.inputStream.bufferedReader().readText()
                val jsonArray = JSONArray(response)
                val productList = mutableListOf<Product>()

                for (i in 0 until jsonArray.length()) {
                    val jsonObj = jsonArray.getJSONObject(i)
                    val product = Product.fromJson(jsonObj)
                    productList.add(product)
                }

                Handler(Looper.getMainLooper()).post {
                    callback(productList)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Handler(Looper.getMainLooper()).post {
                    callback(emptyList())
                }
            }
        }
    }

    fun fetchProductById(id: Int, callback: (Product?) -> Unit) {
        val url = URL("https://fakestoreapi.com/products/$id")
        thread {
            try {
                val conn = url.openConnection() as HttpURLConnection
                conn.connectTimeout = 5000
                conn.readTimeout = 5000
                conn.requestMethod = "GET"

                val response = conn.inputStream.bufferedReader().readText()
                val jsonObj = JSONObject(response)
                val product = Product.fromJson(jsonObj)

                Handler(Looper.getMainLooper()).post {
                    callback(product)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Handler(Looper.getMainLooper()).post {
                    callback(null)
                }
            }
        }
    }
}