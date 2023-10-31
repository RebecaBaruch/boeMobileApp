package com.example.boeteste.components.labeledInput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

@Composable
fun LabeledInput(
    label: String,
    value: String,
    icon: Int? = null,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(vertical = 13.dp)
    ) {
        // Label com ícone opcional
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.width(8.dp)) // Adicionando um espaço entre o ícone e o texto

            icon?.let {
                // Se houver um ícone fornecido, exiba-o
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null, // Você pode adicionar uma descrição se necessário
                    tint = Color.Gray,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(vertical = 13.dp)
        ) {
            // Campo de entrada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it // Atualizando o estado quando o valor muda
                        onValueChange(it) // Chamando a função de retorno de chamada
                    },
                    cursorBrush = SolidColor(Color.Gray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent) // Define o fundo como transparente
                )
            }

            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}