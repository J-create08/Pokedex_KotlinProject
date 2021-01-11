package com.project.pokedex.network.models

data class PokemonListResponse(val count: Int, val results: List<PokemonInfo>)