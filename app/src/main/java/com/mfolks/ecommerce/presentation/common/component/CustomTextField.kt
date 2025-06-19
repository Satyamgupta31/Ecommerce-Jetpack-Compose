package com.molks.ecommerce.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.molks.ecommerce.R
import com.molks.ecommerce.presentation.ui.theme.PrimaryColor
import com.molks.ecommerce.presentation.ui.theme.PrimaryLightColor
import com.molks.ecommerce.presentation.ui.theme.TextColor

@Composable
fun CustomTextField(
    placeholder: String,
    trailingIcon: Int,
    label: String,
    keyboardType: KeyboardType,
    errorState: MutableState<Boolean>,
    visualTransformation: VisualTransformation,
    onChanged: (TextFieldValue) -> Unit
) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
            onChanged(it)
        },
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = trailingIcon),
                contentDescription = "icon"
            )
        },
        visualTransformation = visualTransformation,
        isError = errorState.value,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.primary,
            focusedLabelColor = MaterialTheme.colors.primary,
            textColor = MaterialTheme.colors.onBackground
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
