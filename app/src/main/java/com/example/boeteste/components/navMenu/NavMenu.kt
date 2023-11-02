package com.example.boeteste.components.navMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.R

@Composable
fun NavMenu(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
){
    Row(
        modifier = Modifier.run {
            fillMaxWidth()
                .height(56.dp)
                .background(Color.Gray)
                .padding(16.dp)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Ícones de navegação
        listOf("Home", "Cow", "Add", "Helm", "Profile").forEachIndexed { index, text ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onTabSelected(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                contentDescription = null,
                tint = if (index == selectedTabIndex) Color.White else Color.Gray
                )

                if (index == 2) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Blue)
                            .offset(y = (-8).dp)
                            .clickable {
                                // Ação ao clicar no botão +
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("+", color = Color.White, fontSize = 24.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NavMenuPreview(){
    fun onTabSelected(index: Int) {
        println("Ícone $index selecionado")
    }

    val selectedTabIndex by remember { mutableStateOf(0) }

    NavMenu(selectedTabIndex = selectedTabIndex, onTabSelected = ::onTabSelected)

}