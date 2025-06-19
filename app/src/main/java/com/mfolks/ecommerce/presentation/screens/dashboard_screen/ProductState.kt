package com.molks.ecommerce.presentation.screens.dashboard_screen

import com.molks.ecommerce.domain.model.ProductModel

data class ProductState(
    val isLoading: Boolean = false,
    val product: List<ProductModel>? = null,
    val errorMessage: String = ""
)
