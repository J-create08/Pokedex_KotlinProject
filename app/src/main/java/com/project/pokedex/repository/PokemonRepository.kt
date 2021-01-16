package com.project.pokedex.repository

import androidx.lifecycle.LiveData
import com.project.pokedex.db.Pokemon
import com.project.pokedex.db.PokemonDAO
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val pokemonDAO: PokemonDAO) {
    suspend fun insert(pokemon: Pokemon){
        pokemonDAO.insert(pokemon)
    }

    suspend fun delete(pokemon:Pokemon){
        pokemonDAO.deleteFavoritePokemon(pokemon)
    }

    fun pokemonExist(id: Int): LiveData<Boolean>{
        return (pokemonDAO.exists(id))
    }

    val allPokemon: Flow<List<Pokemon>> = pokemonDAO.getAllPokemon()

}