package com.project.pokedex.network.viewmodels

import com.project.pokedex.network.RetrofitProvider
import com.project.pokedex.network.models.PokemonDetailsResponse
import io.reactivex.rxjava3.core.Observable

class PokemonDetailViewModel {
    private val retrofitProvider = RetrofitProvider()



    fun getPokemonDetail(pokeID: String): Observable<PokemonDetailsResponse> {
        return retrofitProvider.getPokemonService().getPokemonDetails(pokeID)
    }
}