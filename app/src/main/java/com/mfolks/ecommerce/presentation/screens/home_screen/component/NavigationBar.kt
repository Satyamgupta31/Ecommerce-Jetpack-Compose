package com.molks.ecommerce.presentation.dashboard_screen.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.molks.ecommerce.common.Constrains
import com.molks.ecommerce.presentation.graphs.detail_graph.DetailScreen
import com.molks.ecommerce.presentation.graphs.home_graph.ShopHomeScreen
import com.molks.ecommerce.presentation.screens.home_screen.BottomNavItem
import com.molks.ecommerce.presentation.ui.theme.PrimaryColor
import com.molks.ecommerce.presentation.ui.theme.TextColor

@Composable
fun NavigationBar(
    navController: NavController,
    isVisible: (Boolean) -> Unit,
) {
    val navItemList = listOf(
        BottomNavItem.HomeNav,
        BottomNavItem.FavouriteNav,
        BottomNavItem.ChatNav,
        BottomNavItem.ProfileNav,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var bottomNavVisibility by remember { mutableStateOf(true) }

    if (bottomNavVisibility) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary, // Changed from Color.White
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .shadow(
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    elevation = 12.dp,
                )
                .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
        ) {
            navItemList.forEach { screen ->
                val isSelected = navBackStackEntry?.destination?.route == screen.route

                BottomNavigationItem(
                    selected = isSelected,
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            tint = if (isSelected)
                                MaterialTheme.colors.onPrimary // icon color when selected
                            else
                                MaterialTheme.colors.onPrimary.copy(alpha = 0.5f) // faded for unselected
                        )
                    },
                    onClick = {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    selectedContentColor = MaterialTheme.colors.onPrimary,
                    unselectedContentColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                )
            }
        }
    }

    // Handle visibility of bottom & top bar
    when (navBackStackEntry?.destination?.route) {
        ShopHomeScreen.DashboardScreen.route -> {
            bottomNavVisibility = true
            isVisible(true)
        }
        DetailScreen.ProductDetailScreen.route + "/{${Constrains.PRODUCT_ID_PARAM}}" -> {
            bottomNavVisibility = false
            isVisible(false)
        }
        DetailScreen.CartScreen.route -> {
            bottomNavVisibility = false
            isVisible(false)
        }
        DetailScreen.NotificationScreen.route -> {
            bottomNavVisibility = false
            isVisible(false)
        }
        else -> {
            bottomNavVisibility = true
            isVisible(false)
        }
    }
}
