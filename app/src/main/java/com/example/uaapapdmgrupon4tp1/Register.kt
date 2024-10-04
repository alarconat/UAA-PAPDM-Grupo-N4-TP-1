package com.example.uaapapdmgrupon4tp1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uaapapdmgrupon4tp1.ui.theme.UAAPAPDMGrupoN4TP1Theme

// Pantalla Register (RegisterScreen)
@Composable
fun RegisterScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Pantalla de Registro", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    UAAPAPDMGrupoN4TP1Theme {
        RegisterScreen()
    }
}
