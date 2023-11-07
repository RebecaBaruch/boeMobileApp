package com.example.boeteste.components.navMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boeteste.R
import com.example.boeteste.ui.theme.PrimaryBlue

@Composable
fun NavMenu(
    selectedNavItem: NavItem,
    onNavItemClicked: (NavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 33.dp, vertical = 13.dp)
            .clip(RoundedCornerShape(23.dp))
            .drawBehind {
                drawRect(
                    color = Color(0, 0, 0, 10),
                    topLeft = Offset(0f, 0f),
                    size = Size(this.size.width, 4.dp.toPx())
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            //home
            NavIcon(
                icon = R.drawable.home,
                selected = selectedNavItem == NavItem.HOME,
                onClick = { onNavItemClicked(NavItem.HOME) }
            )

            //cowList
            NavIcon(
                icon = R.drawable.cow_icon,
                selected = selectedNavItem == NavItem.COWLIST,
                onClick = { onNavItemClicked(NavItem.COWLIST) }
            )

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(PrimaryBlue)
                    .clickable { /* Ação ao clicar no botão central */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.plus_button_icon),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            NavIcon(
                icon = R.drawable.helmet_icon,
                selected = selectedNavItem == NavItem.HELMETS,
                onClick = { onNavItemClicked(NavItem.HELMETS) }
            )

            NavIcon(
                icon = R.drawable.user_nav_icon,
                selected = selectedNavItem == NavItem.PROFILE,
                onClick = { onNavItemClicked(NavItem.PROFILE) }
            )
        }

        if (selectedNavItem != NavItem.CENTER) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(13.dp, 5.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFF282FD9))
            )
        }
    }
}

@Composable
fun NavIcon(
    icon: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
        )

        if (selected) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(13.dp, 5.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFF282FD9))
            )
        }
    }
}

enum class NavItem {
    HOME, COWLIST, CENTER, HELMETS, PROFILE
}


@Preview
@Composable
fun NavMenuPreview(){
    var selectedNavItem by remember { mutableStateOf(NavItem.HOME) }

    NavMenu(
        selectedNavItem = selectedNavItem,
        onNavItemClicked = { selectedNavItem = it }
    )

}