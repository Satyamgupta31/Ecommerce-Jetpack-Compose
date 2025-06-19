package com.molks.ecommerce.data.repository

import com.molks.ecommerce.data.demo_db.DemoDB
import com.molks.ecommerce.domain.model.ProductModel
import com.molks.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val demoDB: DemoDB
) : ProductRepository {
    override suspend fun getProduct(): List<ProductModel> {
        return demoDB.getProduct()
    }

    override suspend fun getProductDetail(id: Int): ProductModel {
        return demoDB.getProduct()[id-1]
    }
}