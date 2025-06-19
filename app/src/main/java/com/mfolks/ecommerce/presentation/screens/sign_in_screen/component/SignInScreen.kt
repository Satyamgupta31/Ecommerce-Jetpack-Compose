package com.molks.ecommerce.presentation.screens.sign_in_screen.component


import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.molks.ecommerce.R
import com.molks.ecommerce.presentation.common.CustomDefaultBtn
import com.molks.ecommerce.presentation.common.CustomTextField
import com.molks.ecommerce.presentation.common.component.DefaultBackArrow
import com.molks.ecommerce.presentation.common.component.ErrorSuggestion
import com.molks.ecommerce.presentation.graphs.auth_graph.AuthScreen
import com.molks.ecommerce.presentation.ui.theme.PrimaryColor
import com.molks.ecommerce.presentation.ui.theme.PrimaryLightColor
import com.molks.ecommerce.presentation.ui.theme.TextColor


@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var checkBox by remember { mutableStateOf(false) }

    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 🔹 Background Image
        Image(
            painter = painterResource(id = R.drawable.whitebg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 🔹 Main Login Form
        Box(
            modifier = Modifier
//                .padding(24.dp)
                .fillMaxSize()
                .background(
                    color = Color.White.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 🔹 Back Button and Title
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.weight(0.7f)) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.back_icon),
                                contentDescription = "Back",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                    Box(modifier = Modifier.weight(1.0f)) {
                        Text(
                            text = "Sign in",
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "Welcome Back",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Sign in with your email or password\nor continue with social media.",
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(40.dp))

                // 🔹 Email Field
                CustomTextField(
                    placeholder = "example@email.com",
                    trailingIcon = R.drawable.mail,
                    label = "Email",
                    errorState = emailErrorState,
                    keyboardType = KeyboardType.Email,
                    visualTransformation = VisualTransformation.None,
                    onChanged = { newEmail -> email = newEmail }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 🔹 Password Field
                CustomTextField(
                    placeholder = "********",
                    trailingIcon = R.drawable.lock,
                    label = "Password",
                    keyboardType = KeyboardType.Password,
                    errorState = passwordErrorState,
                    visualTransformation = PasswordVisualTransformation(),
                    onChanged = { newPass -> password = newPass }
                )

                Spacer(modifier = Modifier.height(10.dp))

                if (emailErrorState.value) {
                    ErrorSuggestion("Please enter a valid email address.")
                }

                if (passwordErrorState.value) {
                    ErrorSuggestion("Please enter a valid password.")
                }

                // 🔹 Remember Me and Forget Password
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkBox,
                            onCheckedChange = { checkBox = it },
                            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
                        )
                        Text(
                            text = "Remember me",
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 14.sp
                        )
                    }
                    Text(
                        text = "Forget Password",
                        color = MaterialTheme.colors.primary,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        modifier = Modifier.clickable {
                            navController.navigate(AuthScreen.ForgetPasswordScreen.route)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 🔹 Continue Button
                CustomDefaultBtn(shapeSize = 50f, btnText = "Continue") {
                    val pattern = Patterns.EMAIL_ADDRESS
                    val isEmailValid = pattern.matcher(email.text).matches()
                    val isPassValid = password.text.length >= 8
                    emailErrorState.value = !isEmailValid
                    passwordErrorState.value = !isPassValid
                    if (isEmailValid && isPassValid) {
                        navController.navigate(AuthScreen.SignInSuccess.route)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // 🔹 Social Login Icons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    listOf(
                        R.drawable.google_icon,
                        R.drawable.twitter,
                        R.drawable.facebook_2
                    ).forEach { icon ->
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(
                                    MaterialTheme.colors.primary.copy(alpha = 0.1f),
                                    shape = CircleShape
                                )
                                .clickable { /* handle social login */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = icon),
                                contentDescription = "Social Login"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // 🔹 Sign Up Navigation
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't have an account? ",
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "Sign Up",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.clickable {
                            navController.navigate(AuthScreen.SignUpScreen.route)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}


