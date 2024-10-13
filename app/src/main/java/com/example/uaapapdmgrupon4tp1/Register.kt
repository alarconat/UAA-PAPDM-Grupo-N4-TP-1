package com.example.uaapapdmgrupon4tp1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uaapapdmgrupon4tp1.ui.theme.UAAPAPDMGrupoN4TP1Theme

// Modelo de datos para la película
data class Pelicula(
    val titulo: String,
    val director: String,
    val anio: String,
    val genero: String,
    val duracion: String,
    val posterUrl: String
)

@Composable
fun RegistroPeliculasScreen(modifier: Modifier = Modifier) {
    var peliculas by remember { mutableStateOf(listOf<Pelicula>()) }

    Column(modifier = modifier.padding(16.dp)) {
        FormularioRegistro { pelicula ->
            peliculas = peliculas + pelicula
        }
        Spacer(modifier = Modifier.height(16.dp))
        ListaPeliculas(peliculas = peliculas)
    }
}

@Composable
fun FormularioRegistro(onAddPelicula: (Pelicula) -> Unit) {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var posterUrl by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = director,
            onValueChange = { director = it },
            label = { Text("Director") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = anio,
            onValueChange = { anio = it },
            label = { Text("Año de lanzamiento") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text("Género") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = duracion,
            onValueChange = { duracion = it },
            label = { Text("Duración (minutos)") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = posterUrl,
            onValueChange = { posterUrl = it },
            label = { Text("URL del poster") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (titulo.isNotEmpty() && director.isNotEmpty() && anio.isNotEmpty()) {
                    onAddPelicula(
                        Pelicula(titulo, director, anio, genero, duracion, posterUrl)
                    )
                    titulo = ""
                    director = ""
                    anio = ""
                    genero = ""
                    duracion = ""
                    posterUrl = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Guardar Película")
        }
    }
}

@Composable
fun ListaPeliculas(peliculas: List<Pelicula>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(peliculas.size) { index ->
            PeliculaItem(pelicula = peliculas[index])
        }
    }
}

@Composable
fun PeliculaItem(pelicula: Pelicula) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Título: ${pelicula.titulo}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Director: ${pelicula.director}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Año: ${pelicula.anio}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Género: ${pelicula.genero}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Duración: ${pelicula.duracion} minutos", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Poster URL: ${pelicula.posterUrl}", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroPeliculasScreenPreview() {
    UAAPAPDMGrupoN4TP1Theme {
        RegistroPeliculasScreen()
    }
}
