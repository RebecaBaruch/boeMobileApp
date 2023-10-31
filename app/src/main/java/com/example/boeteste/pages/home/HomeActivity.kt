package com.example.boeteste.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.R
import com.example.boeteste.components.header.Header
import com.example.boeteste.components.mixedTitle.MixedTitle
import com.example.boeteste.pages.ui.theme.BoeTesteTheme
import com.example.boeteste.ui.theme.PatternGray

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoeTesteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .padding(
                horizontal = 33.dp
            )
    ) {
        Header()

        Column {
            MixedTitle(
                parteNegrito = "Olá,",
                parteLeve = "Rebeca!",
                fontSize = 33,
                quebrarTexto = false,
                boldFirst = true
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            TotalRegisterBox(
                totalRegisterNum = 7,
                modifier = Modifier.weight(1f)
            )
            
            Spacer(modifier = Modifier.width(3.dp))
            
            TotalRegisterBox(
                totalRegisterNum = 7,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun TotalRegisterBox(totalRegisterNum: Int, modifier: Modifier) {
    val shadowColor = Color(0, 0, 0, 10)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .drawBehind {
                drawRect(
                    color = shadowColor,
                    topLeft = Offset(0f, this.size.height - 4.dp.toPx()),
                    size = Size(this.size.width, this.size.height)
                )
            }
    ) {
        Column(
            modifier = Modifier
                .padding(33.dp)
        ){
            Text(
                text = totalRegisterNum.toString(),
                fontSize = 43.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Registros",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PositiveRegisteredBox(
    totalPositiveNum: Int,
    modifier: Modifier,
    posAddNum: Int,
    negAddnum: Int
) {
    val shadowColor = Color(0, 0, 0, 10)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .drawBehind {
                drawRect(
                    color = shadowColor,
                    topLeft = Offset(0f, this.size.height - 4.dp.toPx()),
                    size = Size(this.size.width, this.size.height)
                )
            }
    ) {
        Column(
            modifier = Modifier
                .padding(33.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = totalPositiveNum.toString(),
                    fontSize = 43.sp,
                    fontWeight = FontWeight.Bold
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Row{
                        Image(
                            painter = painterResource(R.drawable.up_green ),
                            contentDescription = null
                        )

                        Text(
                            text =
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Registros",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}