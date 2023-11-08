package com.example.boeteste.pages.editProfile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.R

@Composable
fun ExcludeAccountButton(
    onClick: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(63.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                Color(0xFFFFE6E6),
                shape = RoundedCornerShape(13.dp)
            )
            .padding(16.dp)

    ) {

        Text(
            text = "Excluir conta",
            color = Color(0xFFFF5454),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(23.dp))

        Image(
            painter = painterResource(R.drawable.trash_icon),
            contentDescription = null
        )
    }
}


@Preview
@Composable
fun ExcludeAccountButtonPreview() {
    ExcludeAccountButton{}
}

