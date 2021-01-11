 package com.project.pokedex.network.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pokedex.network.RetrofitProvider
import com.project.pokedex.network.models.PokemonDetailsResponse
import com.project.pokedex.network.models.PokemonInfo
import com.project.pokedex.network.models.PokemonListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListviewModel : ViewModel(){
    private val retrofitProvider = RetrofitProvider()
//    private val pokemonListResponse: MutableLiveData<List<PokemonInfo>> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()

//    fun getPokemonListResponse(): LiveData<List<PokemonInfo>> {
//        return pokemonListResponse
//    }

    fun getIsError(): LiveData<Boolean> {
        return isError
    }


    fun getPokemonList(): Observable<List<PokemonInfo>> {
        return retrofitProvider.getPokemonService().getPokemonList()
            .map {data -> data.results}
    }

}