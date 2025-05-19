package com.taylan.fakestoreandroid
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {

    private val _horizontalProducts = MutableLiveData<List<Product>>()
    val horizontalProducts: LiveData<List<Product>> = _horizontalProducts

    private val _verticalProducts = MutableLiveData<List<Product>>()
    val verticalProducts: LiveData<List<Product>> = _verticalProducts

    private val _selectedProduct = MutableLiveData<Product>()
    val selectedProduct: LiveData<Product> = _selectedProduct

    fun loadProducts() {
        // Fetch horizontal (limit = 5)
        ProductService.fetchProducts(limit = 5) { products ->
            _horizontalProducts.value = products
        }

        // Fetch vertical (full list)
        ProductService.fetchProducts(limit = 20) { products ->
            _verticalProducts.value = products
        }
    }

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun loadProductById(id: Int, onComplete: () -> Unit = {}) {
        ProductService.fetchProductById(id) { product ->
            product?.let { _selectedProduct.postValue(it) }
            onComplete()
        }
    }
}