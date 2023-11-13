package com.example.boeteste.pages

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boeteste.R
import com.example.boeteste.components.header.Header
import com.example.boeteste.components.mixedTitle.MixedTitle
import com.example.boeteste.pages.home.MenuResponse
import com.example.boeteste.pages.home.MenuViewModel
import com.example.boeteste.pages.ui.theme.BoeTesteTheme
import com.example.boeteste.utils.GlobalState
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoeTesteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    //HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(){
    val dadoIdUsuario = GlobalState.dadosCompartilhados.value
    val menuViewModel: MenuViewModel = viewModel()
    val remoteDataSrc: MenuResponse by lazy { MenuResponse() }
    val thisContext = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            remoteDataSrc.exibirMenuDadosUsuario(dadoIdUsuario) {res, error ->
                if (res != null) {
                    menuViewModel.userName = res.userName
                    menuViewModel.registeredCases = res.registeredCases
                    menuViewModel.positiveCases = res.positiveCases

                    Toast.makeText(thisContext, "Exibindo seus dados!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(thisContext, "Não foi possível exibir seus dados.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(
                horizontal = 33.dp
            )
    ) {
        Header()

        Spacer(modifier = Modifier.height(13.dp))

        Column {

            MixedTitle(
                parteNegrito = "Olá,",
                parteLeve = "${menuViewModel.userName}!",
                fontSize = 33,
                quebrarTexto = false,
                boldFirst = true,
                modifier = Modifier
                    .padding(vertical = 23.dp)
            )

            Spacer(modifier = Modifier.height(13.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TotalRegisterBox(
                    totalRegisterNum = menuViewModel.registeredCases,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(23.dp))

                PositiveRegisteredBox(
                    totalPositiveNum = menuViewModel.positiveCases,
                    posAddNum = 10,
                    negAddnum = 17,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(43.dp))

        GraphicBox()
    }

}

@Composable
fun TotalRegisterBox(
    totalRegisterNum: Int,
    modifier: Modifier
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
            modifier = modifier
                .padding(33.dp)
        ){
            Text(
                text = "${totalRegisterNum}%",
                fontSize = 43.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Registros",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PositiveRegisteredBox(
    totalPositiveNum: Int,
    posAddNum: Int?,
    negAddnum: Int?,
    modifier: Modifier
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
            modifier = modifier
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Image(
                            painter = painterResource(R.drawable.up_green ),
                            contentDescription = null
                        )

                        Text(
                            text = posAddNum.toString(),
                            fontSize = 9.sp,
                            color = Color(0xFF00A140)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Image(
                            painter = painterResource(R.drawable.down_red ),
                            contentDescription = null
                        )

                        Text(
                            text = negAddnum.toString(),
                            fontSize = 9.sp,
                            color = Color(0xFFC30000)
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Casos positivos",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun GraphicBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Gráfico geral",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Row{
                Text(
                    text = "últimos 30 dias",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    color = Color(0xFF717171)
                )

                Spacer(modifier = Modifier.width(7.dp))

                Image(
                    painter = painterResource(R.drawable.gray_chevron_down),
                    contentDescription = null
                )
            }

        }

        Spacer(modifier = Modifier.height(13.dp))

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
        ){
            Image(
                painter = painterResource(R.drawable.graficocasos),
                contentDescription = null
            )
        }

    }
}

@Preview(showBackground = true, widthDp = 424)
@Composable
fun HomeScreenPreview(){
    /* val viewModel: NavigationViewModel by viewModels()
    HomeScreen(viewModel) */
}