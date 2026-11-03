package com.example.picomons.repository

import android.content.ContentValues
import android.content.Context
import com.example.picomons.database.BancoHelper
import com.example.picomons.model.Filme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmeRepository(context: Context) {

    private val bancoHelper = BancoHelper(context)

    suspend fun inserirFilme(filme: Filme) {
        withContext(Dispatchers.IO) {
            val db = bancoHelper.writableDatabase

            val valores = ContentValues().apply {
                put("titulo", filme.titulo)
                put("diretor", filme.diretor)
                put("ano", filme.ano)
            }

            db.insert("filmes", null, valores)
        }
    }

    suspend fun listarFilmes(): List<Filme> {
        return withContext(Dispatchers.IO) {
            val lista = mutableListOf<Filme>()
            val db = bancoHelper.readableDatabase

            val cursor = db.query(
                "filmes",
                null,
                null,
                null,
                null,
                null,
                "id DESC"
            )

            cursor.use {
                while (it.moveToNext()) {
                    val id = it.getInt(it.getColumnIndexOrThrow("id"))
                    val titulo = it.getString(it.getColumnIndexOrThrow("titulo"))
                    val diretor = it.getString(it.getColumnIndexOrThrow("diretor"))
                    val ano = it.getInt(it.getColumnIndexOrThrow("ano"))

                    lista.add(
                        Filme(
                            id = id,
                            titulo = titulo,
                            diretor = diretor,
                            ano = ano
                        )
                    )
                }
            }

            lista
        }
    }

    fun fecharBanco() {
        bancoHelper.close()
    }
}
