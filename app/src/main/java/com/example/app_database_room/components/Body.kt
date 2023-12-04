package com.example.app_database_room.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_database_room.R

@Composable
fun MainTitle(title: String) {
    Text(text = title, color = Color(color = 0xFFa7dbab), fontWeight = FontWeight.Bold)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        colors = TextFieldDefaults.textFieldColors(
            textColor =(Color(color = 0xFF512b52)),
            containerColor = (Color(color = 0xFFB7C9AB)), cursorColor = (Color(color = 0xFF512b52)),
            focusedIndicatorColor = (Color(color = 0xFF512b52)), focusedLabelColor = (Color(color = 0xFF512b52)),
            focusedLeadingIconColor = (Color(color = 0xFF512b52)), unfocusedLeadingIconColor = (Color(color = 0xFF512b52)),
            unfocusedIndicatorColor = (Color(color = 0xFF512b52)), unfocusedLabelColor = (Color(color = 0xFF512b52)),
            unfocusedSupportingTextColor = (Color(color = 0xFF512b52)), unfocusedTrailingIconColor = (Color(color = 0xFF512b52))),
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(bottom = 15.dp)
    )
}


@Composable
fun formatTiempo(tiempo: Long): String {
    val segundos = (tiempo / 1000) % 60
    val minutos = (tiempo / 1000 / 60) % 60
    val horas = tiempo / 1000 / 3600
    return String.format("%02d:%02d:%02d", horas, minutos, segundos)
}

@Composable
fun CronCard(titulo: String, crono: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
            .border(
                width = 2.dp,
                color = (Color(color = 0xFF512b52)),
                shape = RoundedCornerShape(16.dp)
            ) // Adiciona a borda aqui
            .background(color = Color.Gray, shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                text = titulo,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.time),
                    contentDescription = null,
                    tint = (Color(color = 0xFF512b52))
                )
                Text(text = crono, fontSize = 20.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = Color(color = 0xFF512b52)
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


