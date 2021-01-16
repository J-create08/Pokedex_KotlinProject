package com.project.pokedex.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>

    @Delete
    fun deleteFavoritePokemon(pokemon:Pokemon)

    @Query("SELECT EXISTS (SELECT 1 FROM pokemon WHERE id = :id)")
    fun exists(id: Int): LiveData<Boolean>
}