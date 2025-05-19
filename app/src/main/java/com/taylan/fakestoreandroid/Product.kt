package com.taylan.fakestoreandroid

import org.json.JSONObject

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
) {
    companion object {
        fun fromJson(json: JSONObject): Product {
            val ratingJson = json.getJSONObject("rating")
            return Product(
                id = json.getInt("id"),
                title = json.getString("title"),
                price = json.getDouble("price"),
                description = json.getString("description"),
                category = json.getString("category"),
                image = json.getString("image"),
                rating = Rating(
                    rate = ratingJson.getDouble("rate"),
                    count = ratingJson.getInt("count")
                )
            )
        }
    }
}

data class Rating(
    val rate: Double,
    val count: Int
)