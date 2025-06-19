package com.molks.ecommerce.presentation.screens.product_detail_screen

import com.molks.ecommerce.domain.model.ProductModel

data class ProductDetailState(
    val isLoading: Boolean = false,
    val productDetail: ProductModel? = null,
    val errorMessage: String = ""
)