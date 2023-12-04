package com.example.app_database_room.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.app_database_room.ui.theme.rosa
import com.example.app_database_room.ui.theme.verde

@Composable
fun FloatButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(color= 0xFF512b52),
        contentColor =Color(color= 0xFFa7dbab)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Agregar"
        )
    }
}

@Composable
fun MainIconButton(icon: ImageVector, onClick: () -> Unit,  ) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null,  tint = Color(color = 0xFFa7dbab), modifier = Modifier .size(30.dp))
    }
}

@Composable
fun CircleButton(
    icon: Painter,
    enabled: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(8.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFa7dbab), contentColor = (Color(color = 0xFF512b52))),
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
    }
}








