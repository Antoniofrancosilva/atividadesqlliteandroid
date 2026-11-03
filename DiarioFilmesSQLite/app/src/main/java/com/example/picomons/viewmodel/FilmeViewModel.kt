package com.example.picomons.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.picomons.model.Filme
import com.example.picomons.repository.FilmeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FilmeRepository(application)

    private val _filmes = MutableStateFlow<List<Filme>>(emptyList())
    val filmes: StateFlow<List<Filme>> = _filmes

    init {
        carregarFilmes()
    }

    fun carregarFilmes() {
        viewModelScope.launch {
            _filmes.value = repository.listarFilmes()
        }
    }

    fun cadastrarFilme(
        titulo: String,
        diretor: String,
        ano: String
    ) {
        if (titulo.isBlank() || diretor.isBlank() || ano.isBlank()) return

        val anoNumero = ano.toIntOrNull() ?: return

        viewModelScope.launch {
            val filme = Filme(
                titulo = titulo.trim(),
                diretor = diretor.trim(),
                ano = anoNumero
            )

            repository.inserirFilme(filme)
            _filmes.value = repository.listarFilmes()
        }
    }

    override fun onCleared() {
        repository.fecharBanco()
        super.onCleared()
    }
}
