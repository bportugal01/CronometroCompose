package com.example.app_database_room.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.app_database_room.R
import com.example.app_database_room.components.CircleButton
import com.example.app_database_room.components.MainIconButton
import com.example.app_database_room.components.MainTextField
import com.example.app_database_room.components.MainTitle
import com.example.app_database_room.components.formatTiempo
import com.example.app_database_room.model.Cronos
import com.example.app_database_room.viewModels.CronometroViewModel
import com.example.app_database_room.viewModels.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarView(
    navController: NavHostController,
    cronometroVM: CronometroViewModel,
    cronosVM: CronosViewModel,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Adicionar Cronômetro") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = (Color(color = 0xFF512b52))
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { it ->
        ContentAddView(it, navController, cronometroVM, cronosVM)
    }
}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    cronometroVM: CronometroViewModel,
    cronosVM: CronosViewModel,
) {

    val state = cronometroVM.state

    LaunchedEffect(state.cronometroActivo) {
        cronometroVM.cronos()
    }

    Spacer(modifier = Modifier.height(30.dp))

    Column(
        modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(Color(color = 0xFFf3ffeb)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = formatTiempo(cronometroVM.tiempo),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = (Color(color = 0xFF512b52))
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            // iniciar
            CircleButton(
                icon = painterResource(id = R.drawable.play),
                enabled = !state.cronometroActivo
            ) {
                cronometroVM.iniciar()
            }
            // pausar
            CircleButton(
                icon = painterResource(id = R.drawable.pause),
                enabled = state.cronometroActivo
            ) {
                cronometroVM.pausar()
            }

            // detener
            CircleButton(
                icon = painterResource(id = R.drawable.stop),
                enabled = !state.cronometroActivo
            ) {
                cronometroVM.detener()
            }

            // mostrar guardar
            CircleButton(
                icon = painterResource(id = R.drawable.save),
                enabled = state.showSaveButton
            ) {
                cronometroVM.showTextField()
            }

        }

        if (state.showTextField) {
            MainTextField(
                value = state.title,
                onValueChange = { cronometroVM.onValue(it) },
                label = "Título"
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(color = 0xFFa7dbab),
                    contentColor = (Color(color = 0xFF512b52))
                ),
                onClick = {
                    cronosVM.addCrono(
                        Cronos(
                            title = state.title,
                            crono = cronometroVM.tiempo
                        )
                    )

                    navController.popBackStack()

                }) {
                Text(text = "Criar")
            }
            DisposableEffect(Unit) {
                onDispose {
                    cronometroVM.detener()
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Desenvolvido por Bruno Portugal",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}
