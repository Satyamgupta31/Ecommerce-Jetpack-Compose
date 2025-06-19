package com.molks.ecommerce.domain.repository

import com.molks.ecommerce.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProduct(): List<ProductModel>? = null
    suspend fun getProductDetail(id: Int): ProductModel? = null
}