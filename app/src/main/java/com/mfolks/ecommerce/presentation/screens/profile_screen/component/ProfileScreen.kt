package com.molks.ecommerce.presentation.screens.profile_screen.component

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
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.HorizontalAnchorable
import com.molks.ecommerce.presentation.common.component.DefaultBackArrow
import com.molks.ecommerce.presentation.ui.theme.TextColor
import com.molks.ecommerce.presentation.ui.theme.PrimaryColor
import com.molks.ecommerce.R

@Composable
fun ProfileScreen(
    onBackBtnClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.5f)) {
                DefaultBackArrow {
                    onBackBtnClick()
                }
            }
            Box(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = "Profile",
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (image, cameraIcon) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .constrainAs(image) {
                        linkTo(start = parent.start, end = parent.end)
                    }
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.constrainAs(cameraIcon) {
                    bottom.linkTo(image.bottom)
                    end.linkTo(image.end)
                }
            ) {
                IconButton(onClick = { /* Change Picture */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera_icon),
                        contentDescription = "Change Picture",
                        modifier = Modifier
                            .background(MaterialTheme.colors.primary.copy(alpha = 0.2f), CircleShape)
                            .padding(4.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        val optionModifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(MaterialTheme.colors.primary.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
            .padding(5.dp)

        val textModifier = Modifier.weight(0.2f)

        val iconModifier = Modifier.weight(0.05f)

        // Option Rows
        ProfileOptionRow(
            icon = R.drawable.user_icon,
            label = "Profile Picture",
            modifier = optionModifier,
            iconModifier = iconModifier,
            textModifier = textModifier
        )

        Spacer(modifier = Modifier.height(15.dp))

        ProfileOptionRow(
            icon = R.drawable.bell,
            label = "Notification",
            modifier = optionModifier,
            iconModifier = iconModifier,
            textModifier = textModifier
        )

        Spacer(modifier = Modifier.height(15.dp))

        ProfileOptionRow(
            icon = R.drawable.settings,
            label = "Settings",
            modifier = optionModifier,
            iconModifier = iconModifier,
            textModifier = textModifier
        )

        Spacer(modifier = Modifier.height(15.dp))

        ProfileOptionRow(
            icon = R.drawable.question_mark,
            label = "Help Center",
            modifier = optionModifier,
            iconModifier = iconModifier,
            textModifier = textModifier
        )

        Spacer(modifier = Modifier.height(15.dp))

        ProfileOptionRow(
            icon = R.drawable.log_out,
            label = "Logout",
            modifier = optionModifier,
            iconModifier = iconModifier,
            textModifier = textModifier
        )
    }
}

@Composable
fun ProfileOptionRow(
    icon: Int,
    label: String,
    modifier: Modifier,
    iconModifier: Modifier,
    textModifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = iconModifier,
            tint = MaterialTheme.colors.primary
        )
        Text(
            text = label,
            modifier = textModifier,
            color = MaterialTheme.colors.onSurface
        )
        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = null,
            modifier = iconModifier,
            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
    }
}
