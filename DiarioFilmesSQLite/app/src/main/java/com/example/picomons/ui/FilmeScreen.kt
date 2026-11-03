package com.example.picomons.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.picomons.model.Filme
import com.example.picomons.viewmodel.FilmeViewModel

@Composable
fun FilmeScreen(viewModel: FilmeViewModel) {

    var titulo by remember { mutableStateOf("") }
    var diretor by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }

    val filmes by viewModel.filmes.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Diário de Filmes") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "Cadastrar filme assistido",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = diretor,
                onValueChange = { diretor = it },
                label = { Text("Diretor") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = ano,
                onValueChange = { ano = it },
                label = { Text("Ano") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    viewModel.cadastrarFilme(titulo, diretor, ano)
                    titulo = ""
                    diretor = ""
                    ano = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cadastrar filme")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Filmes assistidos",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (filmes.isEmpty()) {
                Text("Nenhum filme cadastrado.")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = filmes,
                        key = { it.id }
                    ) { filme ->
                        FilmeItem(filme)
                    }
                }
            }
        }
    }
}

@Composable
fun FilmeItem(filme: Filme) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = filme.titulo,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text("Diretor: ${filme.diretor}")
            Text("Ano: ${filme.ano}")
        }
    }
}
