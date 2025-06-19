package com.molks.ecommerce.data.demo_db

import androidx.compose.ui.graphics.Color
import com.molks.ecommerce.R
import com.molks.ecommerce.domain.model.ProductModel
import javax.inject.Inject

class DemoDB @Inject constructor() {
    private val description =
        "Top-quality industrial-grade metal product. Durable, corrosion-resistant, and ideal for manufacturing and construction use."

    fun getProduct(): List<ProductModel> {
        return listOf(
            ProductModel(
                id = 1,
                title = "Aluminium Wire 8mm - Conductive Grade",
                description = description,
                images = listOf(
                    R.drawable.aluminium_wire_1,
                    R.drawable.aluminium_wire_1,
                ),
                colors = listOf(Color.Gray, Color.LightGray, Color.White),
                rating = 4.9,
                price = 250.00,
                isFavourite = true,
                isPopular = true,
            ),

            // 2. Aluminium Rod
            ProductModel(
                id = 2,
                title = "Aluminium Rod 12mm - Extruded",
                description = description,
                images = listOf(R.drawable.aluminium_rod),
                colors = listOf(Color.Gray, Color.White, Color(0xFFCFD8DC)),
                rating = 4.7,
                price = 310.00,
                isFavourite = false,
                isPopular = true,
            ),

            // 3. Aluminium Coil
            ProductModel(
                id = 3,
                title = "Aluminium Coil - 99.7% Pure",
                description = description,
                images = listOf(R.drawable.aluminium_coil),
                colors = listOf(Color.Gray, Color.LightGray, Color.White),
                rating = 4.8,
                price = 1200.00,
                isFavourite = true,
                isPopular = true,
            ),

            // 4. Copper Wire
            ProductModel(
                id = 5,
                title = "Copper Wire 6mm - High Conductivity",
                description = description,
                images = listOf(R.drawable.copper_wire),
                colors = listOf(Color(0xFFB87333), Color(0xFFD2691E), Color.White),
                rating = 4.9,
                price = 720.00,
                isFavourite = true,
                isPopular = true,
            ),

            // 6. Copper Sheet
            ProductModel(
                id = 6,
                title = "Copper Sheet 1mm - Premium Finish",
                description = description,
                images = listOf(R.drawable.copper_sheet),
                colors = listOf(Color(0xFFCD7F32), Color.White, Color.Gray),
                rating = 4.7,
                price = 950.00,
                isFavourite = false,
                isPopular = true,
            ),
        )
    }
}
