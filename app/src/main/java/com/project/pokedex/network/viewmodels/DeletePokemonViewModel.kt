package com.project.pokedex.network.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.project.pokedex.db.Pokemon
import com.project.pokedex.db.PokemonDatabase
import com.project.pokedex.repository.PokemonRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.*

class DeletePokemonViewModel (application: Application): AndroidViewModel(application) {
    private val repository: PokemonRepository
    private val database: PokemonDatabase = PokemonDatabase.getDatabase(application)

    init {
        repository = PokemonRepository((database.pokemonDao()))
    }

    fun pokemonExists(id: Int)  {
        viewModelScope.launch {
            repository.pokemonExist(id)
        }
    }


    fun deletePokemon(pokemon:Pokemon) {
        viewModelScope.launch {
            repository.delete(pokemon)
        }
    }

}