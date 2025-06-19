package com.molks.ecommerce.presentation.graphs.detail_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.molks.ecommerce.common.Constrains
import com.molks.ecommerce.presentation.graphs.Graph
import com.molks.ecommerce.presentation.screens.cart_screen.component.CartScreen
import com.molks.ecommerce.presentation.screens.notification_screen.component.NotificationScreen
import com.molks.ecommerce.presentation.screens.product_detail_screen.component.ProductDetailScreen


fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailScreen.ProductDetailScreen.route + "/{${Constrains.PRODUCT_ID_PARAM}}"
    ) {
        composable(DetailScreen.CartScreen.route) {
            CartScreen()
        }
        composable(DetailScreen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(DetailScreen.ProductDetailScreen.route + "/{productId}") {
            ProductDetailScreen() {
                navController.popBackStack()
            }
        }
    }
}