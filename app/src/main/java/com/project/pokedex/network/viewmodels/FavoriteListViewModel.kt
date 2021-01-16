package com.project.pokedex.network.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.project.pokedex.db.Pokemon
import com.project.pokedex.db.PokemonDatabase
import com.project.pokedex.repository.PokemonRepository

class FavoriteListViewModel (application: Application): AndroidViewModel(application){

    private val repository: PokemonRepository
    private val database: PokemonDatabase = PokemonDatabase.getDatabase(application)

    init {
        repository = PokemonRepository((database.pokemonDao()))
    }

    fun getallPokemon(): LiveData<List<Pokemon>> = repository.allPokemon.asLiveData()
}