package com.project.pokedex.network.viewmodels

import androidx.lifecycle.ViewModel
import com.project.pokedex.network.RetrofitProvider
import com.project.pokedex.network.models.PokemonDetailsResponse
import io.reactivex.rxjava3.core.Observable

class PokemonDetailViewModel: ViewModel() {
    private val retrofitProvider = RetrofitProvider()



    fun getPokemonDetail(pokeID: String): Observable<PokemonDetailsResponse> {
        return retrofitProvider.getPokemonService().getPokemonDetails(pokeID)
    }
}