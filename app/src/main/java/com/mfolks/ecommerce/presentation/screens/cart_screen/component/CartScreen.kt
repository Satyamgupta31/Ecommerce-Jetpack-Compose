package com.molks.ecommerce.presentation.screens.cart_screen.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.molks.ecommerce.presentation.common.component.DefaultBackArrow
import com.molks.ecommerce.presentation.ui.theme.TextColor
import com.molks.ecommerce.R
import com.molks.ecommerce.presentation.common.CustomDefaultBtn
import com.molks.ecommerce.presentation.ui.theme.PrimaryColor
import com.molks.ecommerce.presentation.ui.theme.PrimaryLightColor


@Preview(showBackground = true)
@Composable
fun CartScreen() {
    val backgroundImage = painterResource(id = R.drawable.whitebg)

    Box(modifier = Modifier
//                .padding(24.dp)
        .fillMaxSize()
        .background(
            color = Color.White.copy(alpha = 0.85f),
            shape = RoundedCornerShape(20.dp)
        )) {

        // Background image layer
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content with alpha
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)) // Optional dark overlay for contrast
                .padding(bottom = 90.dp) // Reserve space for bottom section
        ) {
            val (topBar, product) = createRefs()

            // Top Bar
            Row(
                modifier = Modifier
                    .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth()
                    .background(Color.White)
                    .constrainAs(topBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(0.5f)) {
                    DefaultBackArrow {}
                }
                Box(modifier = Modifier.weight(0.7f)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Your Cart", color = MaterialTheme.colors.TextColor, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("4 items", color = MaterialTheme.colors.TextColor)
                    }
                }
            }

            // Product List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(product) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                CartItem("Aluminium Rod - 10mm", "₹2,500.00", R.drawable.aluminium_rod)
                CartItem("Copper Wire - 5m Roll", "₹1,200.00", R.drawable.copper_wire)
                CartItem("Aluminium Coil - 50kg", "₹4,200.00", R.drawable.aluminium_coil)
            }
        }

        // Checkout Section - Absolutely anchored at the bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(
                    color = MaterialTheme.colors.PrimaryLightColor.copy(alpha = 0.95f),
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                )
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.receipt),
                    contentDescription = null,
                    tint = MaterialTheme.colors.PrimaryColor,
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(15.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Add voucher code")
                    Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Total")
                    Text(
                        "₹7,900.00",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.PrimaryColor
                    )
                }
                Box(modifier = Modifier.width(150.dp)) {
                    CustomDefaultBtn(shapeSize = 15f, btnText = "Check Out") {}
                }
            }
        }
    }
}
@Composable
fun CartItem(title: String, price: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
//            .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp)) // Optional: subtle shadow
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = price, color = MaterialTheme.colors.PrimaryColor, fontWeight = FontWeight.Bold)
                    Text("  x1", color = MaterialTheme.colors.TextColor)
                }
            }
        }
    }
}
