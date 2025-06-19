package com.molks.ecommerce.presentation.screens.dashboard_screen.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.toColor
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.molks.ecommerce.R
import com.molks.ecommerce.presentation.graphs.Graph
import com.molks.ecommerce.presentation.graphs.detail_graph.DetailScreen
import com.molks.ecommerce.presentation.screens.dashboard_screen.DashboardViewModel
import com.molks.ecommerce.presentation.ui.theme.PrimaryColor
import com.molks.ecommerce.presentation.ui.theme.PrimaryLightColor
import com.molks.ecommerce.presentation.ui.theme.SecondaryColor
import com.molks.ecommerce.presentation.ui.theme.TextColor
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun DashboardScreen(
    popularProductState: LazyListState = rememberLazyListState(),
    suggestionProductState: LazyListState = rememberLazyListState(),
    productViewModel: DashboardViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {

    val state = productViewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary, shape = RoundedCornerShape(10.dp))
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Strength Delivered, Trust Forged", color = Color.White)
            Text(
                "Up to 25% Off on Bulk Orders",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            val items = listOf(
                R.drawable.bill_icon to "Flash\nDeal",
                R.drawable.bill_icon to "Bill",
                R.drawable.discover to "More"
            )
            items.forEach { (icon, label) ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = label,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.secondary,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .size(50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {}
                            .padding(10.dp)
                    )
                    Text(text = label, fontSize = 14.sp, textAlign = TextAlign.Center)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Special for you", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colors.onBackground)
            Text(text = "See More", color = MaterialTheme.colors.secondary)
        }

        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            state = popularProductState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            item {
                SpecialOfferCard(
                    title = "Aluminium Wires",
                    subtitle = "High Conductivity",
                    image = R.drawable.image_banner_alliwire
                )
            }
            item {
                SpecialOfferCard(
                    title = "Aluminium Rods",
                    subtitle = "Industrial Grade",
                    image = R.drawable.image_banner_allirod
                )
            }
            item {
                SpecialOfferCard(
                    title = "Aluminium Coils",
                    subtitle = "Bulk Orders Available",
                    image = R.drawable.image_banner_allicoil
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Popular Product", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colors.onBackground)
            Text(text = "See More", color = MaterialTheme.colors.secondary)
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            state = suggestionProductState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            items(state.product!!.size) {
                var favouriteRemember by remember { mutableStateOf(state.product[it].isFavourite) }

                Column {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                onItemClick(state.product[it].id)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = state.product[it].images[0]),
                            contentDescription = state.product[it].description
                        )
                    }

                    Text(
                        text = state.product[it].title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(150.dp),
                        color = MaterialTheme.colors.onBackground
                    )

                    Row(
                        modifier = Modifier
                            .width(150.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "₹ ${state.product[it].price}",
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colors.primary
                        )

                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    MaterialTheme.colors.secondary,
                                    shape = CircleShape
                                )
                                .clip(CircleShape)
                                .clickable {
                                    favouriteRemember = !favouriteRemember
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(
                                    id = if (favouriteRemember)
                                        R.drawable.heart_icon_2
                                    else R.drawable.heart_icon
                                ),
                                contentDescription = "Favourite Icon",
                                modifier = Modifier.padding(3.dp),
                                colorFilter = if (favouriteRemember) ColorFilter.tint(Color.Red) else null
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SpecialOfferCard(title: String, subtitle: String, image: Int) {
    ConstraintLayout(
        modifier = Modifier
            .width(280.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        val (bannerText, bannerImage) = createRefs()
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.constrainAs(bannerImage) {}
        )
        Column(
            modifier = Modifier
                .background(Color(0x8DB3B0B0))
                .padding(15.dp)
                .constrainAs(bannerText) {
                    top.linkTo(bannerImage.top)
                    bottom.linkTo(bannerImage.bottom)
                    start.linkTo(bannerImage.start)
                    end.linkTo(bannerImage.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.heightIn(15.dp))
            Text(text = subtitle, color = Color.White)
        }
    }
}