package com.example.boeteste.components.linkText

import android.content.Context
import android.content.Intent
import android.service.autofill.OnClickAction
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.boeteste.ui.theme.boeLightBlue

@Composable
fun LinkText(
    linkText: String,
    modifier: Modifier

) {
    Text(
        text = AnnotatedString(linkText),
        style = TextStyle(color = boeLightBlue, fontWeight = FontWeight.Bold),
        modifier = modifier
    )
}