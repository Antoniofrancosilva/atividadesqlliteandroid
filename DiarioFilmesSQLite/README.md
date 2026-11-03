# Diário de Filmes Assistidos

Aplicativo Android desenvolvido para a atividade avaliativa "Missão: Sua Própria Base de Dados".

## Tema

Diário de Filmes Assistidos.

## Funcionalidades

- Cadastrar filmes assistidos.
- Informar título, diretor e ano.
- Listar os filmes cadastrados.
- Persistir os dados localmente com SQLite.
- Manter os dados após fechar e reabrir o aplicativo.

## Arquitetura

O projeto está separado em:

- `model`
- `database`
- `repository`
- `viewmodel`
- `ui`

Fluxo:

`UI → ViewModel → Repository → SQLiteOpenHelper → SQLite`

## Banco de dados

O banco é criado do zero usando somente classes nativas do Android:

- `SQLiteOpenHelper`
- `SQLiteDatabase`
- `ContentValues`
- `Cursor`

O `onCreate()` cria a tabela com `execSQL()` e o `onUpgrade()` está implementado.

Não foram utilizadas bibliotecas de persistência como Room ou SQLDelight.

## Coroutines

As operações de leitura e escrita do banco são `suspend` e executadas com `Dispatchers.IO`.

## Tecnologias

- Kotlin
- Jetpack Compose
- SQLite nativo
- SQLiteOpenHelper
- Repository
- ViewModel
- Coroutines

## Como executar

1. Abra o projeto no Android Studio.
2. Aguarde a sincronização do Gradle.
3. Conecte um dispositivo Android ou inicie um emulador.
4. Execute o projeto.

## Estrutura

```text
com.example.picomons
├── database
│   └── BancoHelper.kt
├── model
│   └── Filme.kt
├── repository
│   └── FilmeRepository.kt
├── viewmodel
│   └── FilmeViewModel.kt
├── ui
│   └── FilmeScreen.kt
└── MainActivity.kt
```
