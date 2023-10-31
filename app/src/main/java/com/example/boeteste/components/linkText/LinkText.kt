package com.example.boeteste.components.linkText

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.boeteste.ui.theme.boeLightBlue

@Composable
fun LinkText(
    linkText: String,
    linkHref: Class<*>? = null,
    context: Context? = null
) {
    ClickableText(
        text = AnnotatedString(linkText),
        style = TextStyle(color = boeLightBlue, fontWeight = FontWeight.Bold),
        onClick = {
            linkHref?.let { href ->
                context?.let {
                    val intent = Intent(it, href)
                    it.startActivity(intent)
                }
            }
        }
    )
}