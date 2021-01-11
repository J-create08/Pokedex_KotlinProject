package com.project.pokedex.network

import com.project.pokedex.network.models.PokemonDetailsResponse
import com.project.pokedex.network.models.PokemonListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon/")
    fun getPokemonList(
        @Query("offset") offset: String = "0",
        @Query("limit") limit: String = "100"
    ): Observable<PokemonListResponse>

    @GET("pokemon/{pokemonID}")
    fun getPokemonDetails(@Path ("pokemonID") pokeID: String): Observable<PokemonDetailsResponse>
}