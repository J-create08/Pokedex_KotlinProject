package com.project.pokedex.network.models

data class PokemonDetailsResponse(val id: Int, val name: String, val height: Int, val weight: Int, val baseExp: Int, val sprites: PokemonImageResponse)