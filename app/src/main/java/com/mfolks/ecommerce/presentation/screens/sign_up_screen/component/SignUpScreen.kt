package com.molks.ecommerce.presentation.screens.sign_up_screen.component

import android.graphics.drawable.Icon
import android.util.Patterns
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SignUpScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPass by remember { mutableStateOf(TextFieldValue("")) }
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val conPasswordErrorState = remember { mutableStateOf(false) }
    val firstNameErrorState = remember { mutableStateOf(false) }
    val lastNameErrorState = remember { mutableStateOf(false) }
    val phoneNumberErrorState = remember { mutableStateOf(false) }
    val addressErrorState = remember { mutableStateOf(false) }
    val animate = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.whitebg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
//                .padding(24.dp)
                .fillMaxSize()
                .background(
                    color = Color.White.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            AnimatedContent(targetState = animate.value, transitionSpec = {
                slideInHorizontally(initialOffsetX = { it }) with slideOutHorizontally(targetOffsetX = { -it })
            }) {
                if (it) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

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
                                    text = "Sign Up",
                                    color = MaterialTheme.colors.onBackground,
                                    fontSize = 18.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))
                        Text(text = "Register Account", fontSize = 26.sp, fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Complete your Details\nor continue with social media.",
                            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        CustomTextField(
                            placeholder = "example@email.com",
                            trailingIcon = R.drawable.mail,
                            label = "Email",
                            errorState = emailErrorState,
                            keyboardType = KeyboardType.Email,
                            visualTransformation = VisualTransformation.None,
                            onChanged = { email = it }
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        CustomTextField(
                            placeholder = "********",
                            trailingIcon = R.drawable.lock,
                            label = "Password",
                            keyboardType = KeyboardType.Password,
                            errorState = passwordErrorState,
                            visualTransformation = PasswordVisualTransformation(),
                            onChanged = { password = it }
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        CustomTextField(
                            placeholder = "********",
                            trailingIcon = R.drawable.lock,
                            label = "Confirm Password",
                            keyboardType = KeyboardType.Password,
                            errorState = conPasswordErrorState,
                            visualTransformation = PasswordVisualTransformation(),
                            onChanged = { confirmPass = it }
                        )

                        Spacer(modifier = Modifier.height(30.dp))
                        if (emailErrorState.value) ErrorSuggestion("Please enter valid email address.")
                        if (passwordErrorState.value) ErrorSuggestion("Please enter valid password.")
                        if (conPasswordErrorState.value) ErrorSuggestion("Confirm Password miss matched.")

                        CustomDefaultBtn(shapeSize = 50f, btnText = "Continue") {
                            val pattern = Patterns.EMAIL_ADDRESS
                            val isEmailValid = pattern.matcher(email.text).matches()
                            val isPassValid = password.text.length >= 8
                            val conPassMatch = password == confirmPass

                            emailErrorState.value = !isEmailValid
                            passwordErrorState.value = !isPassValid
                            conPasswordErrorState.value = !conPassMatch

                            if (isEmailValid && isPassValid && conPassMatch) {
                                animate.value = !animate.value
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

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

                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                                    text = "Sign Up",
                                    color = MaterialTheme.colors.onBackground,
                                    fontSize = 18.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(text = "Complete Profile", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Enter All your details Correctly.",
                            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        CustomTextField(
                            placeholder = "Enter your first name",
                            trailingIcon = R.drawable.user,
                            label = "First Name",
                            errorState = firstNameErrorState,
                            keyboardType = KeyboardType.Text,
                            visualTransformation = VisualTransformation.None,
                            onChanged = { newText -> firstName = newText }
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        CustomTextField(
                            placeholder = "Enter your last name",
                            trailingIcon = R.drawable.user,
                            label = "Last Name",
                            errorState = lastNameErrorState,
                            keyboardType = KeyboardType.Text,
                            visualTransformation = VisualTransformation.None,
                            onChanged = { newText -> lastName = newText }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        CustomTextField(
                            placeholder = "Enter your phone number",
                            trailingIcon = R.drawable.phone,
                            label = "Phone Number",
                            keyboardType = KeyboardType.Phone,
                            errorState = phoneNumberErrorState,
                            visualTransformation = VisualTransformation.None,
                            onChanged = { newNumber -> phoneNumber = newNumber }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        CustomTextField(
                            placeholder = "example: Delhi, India",
                            trailingIcon = R.drawable.location_point,
                            label = "Address",
                            keyboardType = KeyboardType.Text,
                            errorState = addressErrorState,
                            visualTransformation = VisualTransformation.None,
                            onChanged = { newText -> address = newText }
                        )

                        Spacer(modifier = Modifier.height(30.dp))
                        if (firstNameErrorState.value || lastNameErrorState.value) {
                            ErrorSuggestion("Please enter valid name.")
                        }
                        if (phoneNumberErrorState.value) {
                            ErrorSuggestion("Please enter valid phone number.")
                        }
                        if (addressErrorState.value) {
                            ErrorSuggestion("Please enter valid address.")
                        }

                        CustomDefaultBtn(shapeSize = 50f, btnText = "Continue") {
                            val isPhoneValid = phoneNumber.text.isEmpty() || phoneNumber.text.length < 4
                            val isFNameValid = firstName.text.isEmpty() || firstName.text.length < 3
                            val isLNameValid = lastName.text.isEmpty() || lastName.text.length < 3
                            val isAddressValid = address.text.isEmpty() || address.text.length < 5
                            firstNameErrorState.value = !isFNameValid
                            lastNameErrorState.value = !isLNameValid
                            addressErrorState.value = !isAddressValid
                            phoneNumberErrorState.value = !isPhoneValid
                            if (!isFNameValid && !isLNameValid && !isAddressValid && !isPhoneValid) {
                                navController.navigate(AuthScreen.OTPScreen.route)
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 50.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp)
                                .clickable {},
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "By continuing you confirm that you agree",
                                color = MaterialTheme.colors.TextColor
                            )
                            Row {
                                Text(text = "with our ", color = MaterialTheme.colors.TextColor)
                                Text(
                                    text = "Terms & Condition",
                                    color = MaterialTheme.colors.PrimaryColor,
                                    modifier = Modifier.clickable {}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
