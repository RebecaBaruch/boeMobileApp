package com.example.boeteste.components.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boeteste.R

@Composable
fun Header(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 33.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .weight(1f), // Ocupa o máximo de espaço disponível
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(R.drawable.boe_symbol),
                contentDescription = "Boe símbolo",
                modifier = Modifier.size(23.dp)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f), // Ocupa o máximo de espaço disponível
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(R.drawable.bell_icon),
                contentDescription = "Bell icon",
                modifier = Modifier.size(23.dp)
            )
        }
    }
}