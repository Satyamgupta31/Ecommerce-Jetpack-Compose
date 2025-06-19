package com.molks.ecommerce.presentation.screens.on_boarding_screen.component

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.molks.ecommerce.R
import com.molks.ecommerce.presentation.common.CustomDefaultBtn
import com.molks.ecommerce.presentation.graphs.auth_graph.AuthScreen
import com.molks.ecommerce.presentation.screens.on_boarding_screen.viewmodel.OnboardingViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.molks.ecommerce.presentation.ui.theme.PrimaryColor

@Composable
fun SplashScreen(navController: NavController, viewModel: OnboardingViewModel = hiltViewModel()) {
    val stage by viewModel.stage.collectAsState()

    if (stage == 0) {
        BrandingScreen(onContinue = { viewModel.moveToOnboarding() })
    } else {
        MfolksOnboarding(navController = navController, onboardingViewModel = viewModel)
    }
}


// ===================== BrandingScreen.kt =====================
@Composable
fun BrandingScreen(onContinue: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000) // Wait for 3 seconds
        onContinue() // Automatically move to onboarding
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = "MFOLKS Logo",
                modifier = Modifier
                    .width(250.dp)
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "MFOLKS INDUSTRIES",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "PRIVATE LIMITED",
                color = Color.White,
                fontSize = 16.sp,
                letterSpacing = 2.sp
            )
        }
    }
}

// ===================== MfolksOnboarding.kt =====================
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun MfolksOnboarding(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel = hiltViewModel() // or hiltViewModel() if using Hilt
) {
    val splashImageList = listOf(
        R.drawable.splash_1,
        R.drawable.splash_2,
        R.drawable.splash_3
    )
    val messages = listOf(
        "Recipient of Goods or Services",
        "Retail & Wholesale Business",
        "Supplier of Services | Import & Export"
    )

    val pagerState = rememberPagerState()

    // Keep current page state in ViewModel updated
    LaunchedEffect(pagerState.currentPage) {
        onboardingViewModel.currentPage.value = pagerState.currentPage
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            HorizontalPager(
                count = splashImageList.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_1),
                        contentDescription = "MFOLKS Logo",
                        modifier = Modifier
                            .width(250.dp)
                            .height(100.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = messages[page],
                        color = Color.White,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.muli))
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Image(
                        painter = painterResource(id = splashImageList[page]),
                        contentDescription = "Splash Image",
                        modifier = Modifier.padding(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            DotIndicator(
                totalDots = splashImageList.size,
                selectedIndex = onboardingViewModel.currentPage.value,
                activeColor = Color.White,
                inactiveColor = Color.White.copy(alpha = 0.4f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (onboardingViewModel.currentPage.value == splashImageList.lastIndex) {
                Button(
                    onClick = {
                        navController.navigate(AuthScreen.SignInScreen.route) {
                            popUpTo(AuthScreen.OnBoardingScreen.route) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color(0xFF4DB6AC) // or PrimaryColor if defined
                    )
                ) {
                    Text("Continue")
                }
            } else {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
fun DotIndicator(
    totalDots: Int,
    selectedIndex: Int,
    activeColor: Color = Color.White,
    inactiveColor: Color = Color.White.copy(alpha = 0.4f)
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (index == selectedIndex) 12.dp else 8.dp)
                    .background(
                        color = if (index == selectedIndex) activeColor else inactiveColor,
                        shape = CircleShape
                    )
            )
        }
    }
}
