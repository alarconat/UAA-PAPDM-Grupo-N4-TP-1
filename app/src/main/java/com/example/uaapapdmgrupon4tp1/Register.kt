package com.example.uaapapdmgrupon4tp1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uaapapdmgrupon4tp1.ui.theme.UAAPAPDMGrupoN4TP1Theme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter


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
    // Lista mutable de películas
    var peliculas by remember { mutableStateOf(listOf<Pelicula>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Formulario de registro
        FormularioRegistro { pelicula ->
            peliculas = peliculas + pelicula
        }
        //Spacer(modifier = Modifier.height(16.dp))
        // Agregamos un Spacer para empujar el formulario más abajo
        Spacer(modifier = Modifier.weight(1f)) // Empuja el contenido hacia abajo

        // Lista de películas con funcionalidad de eliminar
        ListaPeliculas(
            peliculas = peliculas,
            onEliminarPelicula = { peliculaAEliminar ->
                peliculas = peliculas.filter { it != peliculaAEliminar }
            }
        )
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

    // Variables para manejar los errores
    var tituloError by remember { mutableStateOf(false) }
    var directorError by remember { mutableStateOf(false) }
    var anioError by remember { mutableStateOf(false) }
    var generoError by remember { mutableStateOf(false) }
    var duracionError by remember { mutableStateOf(false) }
    var posterUrlError by remember { mutableStateOf(false) }
    var anioInvalido by remember { mutableStateOf(false) }
    var duracionInvalida by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // Campo Título
        TextField(
            value = titulo,
            onValueChange = {
                titulo = it
                tituloError = it.isEmpty() // Se actualiza el estado del error
            },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth(),
            isError = tituloError // Muestra el campo en rojo si hay error
        )
        if (tituloError) {
            Text("Este campo no puede quedar vacío", color = MaterialTheme.colorScheme.error)
        }

        // Campo Director
        TextField(
            value = director,
            onValueChange = {
                director = it
                directorError = it.isEmpty() // Se actualiza el estado del error
            },
            label = { Text("Director") },
            modifier = Modifier.fillMaxWidth(),
            isError = directorError // Muestra el campo en rojo si hay error
        )
        if (directorError) {
            Text("Este campo no puede quedar vacío", color = MaterialTheme.colorScheme.error)
        }

        // Campo Año
        TextField(
            value = anio,
            onValueChange = {
                anio = it
                anioError = it.isEmpty() // Se actualiza el estado del error
                anioInvalido = it.isNotEmpty() && it.toIntOrNull() == null // Verifica si no es un número válido
            },
            label = { Text("Año de lanzamiento") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number // Teclado solo numérico
            ),
            isError = anioError || anioInvalido // Muestra el campo en rojo si hay error
        )
        if (anioError) {
            Text("Este campo no puede quedar vacío", color = MaterialTheme.colorScheme.error)
        } else if (anioInvalido) {
            Text("Ingrese solo números enteros", color = MaterialTheme.colorScheme.error)
        }

        // Campo Género
        TextField(
            value = genero,
            onValueChange = {
                genero = it
                generoError = it.isEmpty() // Se actualiza el estado del error
            },
            label = { Text("Género") },
            modifier = Modifier.fillMaxWidth(),
            isError = generoError // Muestra el campo en rojo si hay error
        )
        if (generoError) {
            Text("Este campo no puede quedar vacío", color = MaterialTheme.colorScheme.error)
        }

        // Campo Duración
        TextField(
            value = duracion,
            onValueChange = {
                duracion = it
                duracionError = it.isEmpty() // Se actualiza el estado del error
                duracionInvalida = it.isNotEmpty() && it.toIntOrNull() == null // Verifica si no es un número válido
            },
            label = { Text("Duración (minutos)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number // Teclado solo numérico
            ),
            isError = duracionError || duracionInvalida // Muestra el campo en rojo si hay error
        )
        if (duracionError) {
            Text("Este campo no puede quedar vacío", color = MaterialTheme.colorScheme.error)
        } else if (duracionInvalida) {
            Text("Ingrese solo números enteros", color = MaterialTheme.colorScheme.error)
        }

        // Campo Poster URL
        TextField(
            value = posterUrl,
            onValueChange = {
                posterUrl = it
                posterUrlError = it.isEmpty() // Se actualiza el estado del error
            },
            label = { Text("URL del poster") },
            modifier = Modifier.fillMaxWidth(),
            isError = posterUrlError // Muestra el campo en rojo si hay error
        )
        if (posterUrlError) {
            Text("Este campo no puede quedar vacío", color = MaterialTheme.colorScheme.error)
        }

        AsyncPicture("https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg")

        // Botón para guardar la película
        Button(
            onClick = {
                // Validar cada campo y mostrar error si está vacío
                tituloError = titulo.isEmpty()
                directorError = director.isEmpty()
                anioError = anio.isEmpty()
                generoError = genero.isEmpty()
                duracionError = duracion.isEmpty()
                posterUrlError = posterUrl.isEmpty()

                // Validación de año y duración
                anioInvalido = anio.isNotEmpty() && anio.toIntOrNull() == null
                duracionInvalida = duracion.isNotEmpty() && duracion.toIntOrNull() == null

                // Si todos los campos están completos y los valores son válidos, agregar la película
                if (!tituloError && !directorError && !anioError && !generoError && !duracionError && !posterUrlError && !anioInvalido && !duracionInvalida) {
                    onAddPelicula(
                        Pelicula(titulo, director, anio, genero, duracion, posterUrl)
                    )
                    // Limpiar los campos después de guardar la película
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
fun ListaPeliculas(peliculas: List<Pelicula>, onEliminarPelicula: (Pelicula) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaciado entre las tarjetas
    ) {
        items(peliculas) { pelicula ->
            TarjetaDePelicula(pelicula = pelicula, onEliminarPelicula = onEliminarPelicula)
        }
    }
}

@Composable
fun TarjetaDePelicula(pelicula: Pelicula, onEliminarPelicula: (Pelicula) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Margen para separar las tarjetas
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            // Título de la película en la parte superior, resaltado
            Text(
                text = pelicula.titulo,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

            // LazyRow para los detalles de la película debajo del título
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { InfoPelicula(titulo = "Director", valor = pelicula.director) }
                item { InfoPelicula(titulo = "Año", valor = pelicula.anio) }
                item { InfoPelicula(titulo = "Género", valor = pelicula.genero) }
                item { InfoPelicula(titulo = "Duración", valor = "${pelicula.duracion} min") }
                item { InfoPelicula(titulo = "Poster URL", valor = pelicula.posterUrl) }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón de eliminación en la parte inferior
            Button(
                onClick = { onEliminarPelicula(pelicula) },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Eliminar")
            }
        }
    }
}

@Composable
fun InfoPelicula(titulo: String, valor: String) {
    Column(
        modifier = Modifier
            .wrapContentWidth() // Ajustar el ancho al contenido
            .padding(end = 8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = titulo, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = valor, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
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

@Composable
fun AsyncPicture(
    imageUrl: String
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    Box(
        //modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(230.dp)
            .background(MaterialTheme.colorScheme.primary)
        ) {
            val imagePainter = rememberAsyncImagePainter(
                model = imageUrl,
                onState = { state ->
                    isLoading = state is AsyncImagePainter.State.Loading
                    isError = state is AsyncImagePainter.State.Error
                    isSuccess = state is AsyncImagePainter.State.Success
                }
            )
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    color = Color.White
                )
            }
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = imagePainter,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroPeliculasScreenPreview() {
    UAAPAPDMGrupoN4TP1Theme {
        RegistroPeliculasScreen()
    }
}
