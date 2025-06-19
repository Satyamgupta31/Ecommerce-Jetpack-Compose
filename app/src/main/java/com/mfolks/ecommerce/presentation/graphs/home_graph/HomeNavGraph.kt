package com.molks.ecommerce.presentation.graphs.home_graph


import android.telecom.Call.Details
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.molks.ecommerce.presentation.graphs.Graph
import com.molks.ecommerce.presentation.graphs.detail_graph.DetailScreen
import com.molks.ecommerce.presentation.graphs.detail_graph.detailNavGraph
import com.molks.ecommerce.presentation.screens.conversation_screen.component.ConversationScreen
import com.molks.ecommerce.presentation.screens.dashboard_screen.component.DashboardScreen
import com.molks.ecommerce.presentation.screens.favourite_screen.component.FavouriteScreen
import com.molks.ecommerce.presentation.screens.profile_screen.component.ProfileScreen

@Composable
fun HomeNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = ShopHomeScreen.DashboardScreen.route
    ) {
        composable(ShopHomeScreen.DashboardScreen.route) {
            DashboardScreen() { productId ->
                navHostController.navigate(DetailScreen.ProductDetailScreen.route + "/${productId}")
            }
        }
        composable(ShopHomeScreen.FavouriteScreen.route) {
            FavouriteScreen()
        }
        composable(ShopHomeScreen.ConversationScreen.route) {
            ConversationScreen()
        }
        composable(ShopHomeScreen.ProfileScreen.route) {
            ProfileScreen() {
                navHostController.popBackStack()
            }
        }
        //detail graph
        detailNavGraph(navController = navHostController)
    }
}