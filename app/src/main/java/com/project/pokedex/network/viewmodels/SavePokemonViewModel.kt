package com.project.pokedex.network.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.project.pokedex.db.Pokemon
import com.project.pokedex.db.PokemonDatabase
import com.project.pokedex.repository.PokemonRepository
import kotlinx.coroutines.launch

class SavePokemonViewModel (application: Application): AndroidViewModel(application) {
    private val repository: PokemonRepository
    private val database: PokemonDatabase = PokemonDatabase.getDatabase(application)

    init {
        repository = PokemonRepository((database.pokemonDao()))
    }

    fun insertPokemon(pokemon:Pokemon) {
        viewModelScope.launch {
            repository.insert(pokemon)
        }
    }


}