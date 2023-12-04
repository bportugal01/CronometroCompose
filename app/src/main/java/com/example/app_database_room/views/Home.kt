package com.example.app_database_room.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.app_database_room.R
import com.example.app_database_room.components.CronCard
import com.example.app_database_room.components.FloatButton
import com.example.app_database_room.components.MainTitle
import com.example.app_database_room.components.formatTiempo
import com.example.app_database_room.ui.theme.rosa
import com.example.app_database_room.ui.theme.verde
import com.example.app_database_room.viewModels.CronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavHostController, cronosVM: CronosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Cronômetro Compose  ") },
                navigationIcon = {
                    IconButton(onClick = {
                        // Adicione a ação desejada quando o ícone do menu for clicado
                        // Por exemplo: scaffoldState.drawerState.open()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu), // Substitua com o seu próprio ícone
                            contentDescription = "Menu",
                            tint = Color(color = 0xFFa7dbab),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = (Color(color = 0xFF512b52))
                )
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentHomeView(it, navController, cronosVM)
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, cronosVM: CronosViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = 0xFFf3ffeb))
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            val cronosList by cronosVM.cronosList.collectAsState()
            if (cronosList.isEmpty()) {
                Text(
                    text = "Sem registros até o momento",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = (Color(color = 0xFF512b52))
                )
            } else {
                LazyColumn {
                    items(cronosList) { item ->
                        val delete = SwipeAction(
                            icon = rememberVectorPainter(Icons.Default.Delete),
                            background = (Color(color = 0xFFf3ffeb)),
                            onSwipe = { cronosVM.deleteCrono(item) }
                        )
                        SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 270.dp) {
                            CronCard(item.title, formatTiempo(item.crono)) {
                                navController.navigate("EditView/${item.id}")
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }

        // Adicionando o texto no rodapé
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = "Desenvolvido por Bruno Portugal",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}




