package com.project.pokedex.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class  RetrofitProvider {
    private val baseUrl = "https://pokeapi.co/api/v2/"
    lateinit var retrofit: Retrofit
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun getPokemonService(): PokemonService = retrofit.create(PokemonService::class.java)

}